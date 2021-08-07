package consulo.internal.arquill.editor.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import consulo.internal.arquill.editor.client.js.JsEditor;
import consulo.internal.arquill.editor.client.js.JsEventProcessor;
import consulo.internal.arquill.editor.shared.ArquillEventListenerServerRpc;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author VISTALL
 * @since 03/12/2020
 */
public class VArquillEditor extends Widget
{
	private final String myId;
	private JsEditor myJsEditor;

	private Map<String, JavaScriptObject> myRegisteredEvents = new HashMap<>();

	protected ArquillEventListenerServerRpc myEventProxy;

	public VArquillEditor()
	{
		Element pre = DOM.createElement("pre");
		pre.getStyle().setMargin(0, Style.Unit.PX);
		pre.getStyle().setPadding(0, Style.Unit.PX);

		setElement(pre);
		myId = DOM.createUniqueId();
		pre.setId(myId);
	}

	public void setText(String text)
	{
		myJsEditor.setText(text);
	}

	@Override
	protected void onLoad()
	{
		if(!isLibraryLoad())
		{
			Window.alert("ArquillEditor not loaded");
		}
		myJsEditor = injectEditor(myId);
	}

	public Set<String> getRegisteredEvents()
	{
		return myRegisteredEvents.keySet();
	}

	public void registerEvent(String eventName)
	{
		if(myRegisteredEvents.containsKey(eventName))
		{
			return;
		}

		JavaScriptObject listener = myJsEditor.addListener(eventName, event -> {
			JsEventProcessor processor = JsEventProcessor.ourProcessors.get(eventName);
			if(processor != null)
			{
				processor.call(myJsEditor, event, myEventProxy);
			}
		});

		myRegisteredEvents.put(eventName, listener);
	}

	public void unregisterEvent(String eventName)
	{
		JavaScriptObject listener = myRegisteredEvents.remove(eventName);
		if(listener != null && myJsEditor != null)
		{
			myJsEditor.removeListener(eventName, listener);
		}
	}

	private static native boolean isLibraryLoad() /*-{
		return $wnd.arquillEditor;
	}-*/;

	private static native JsEditor injectEditor(String id) /*-{
		var editor = $wnd.arquillEditor.createEditor({
			parent: id
		});

		return editor;
	}-*/;
}
