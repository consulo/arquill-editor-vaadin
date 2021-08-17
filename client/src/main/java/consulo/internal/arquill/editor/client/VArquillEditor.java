package consulo.internal.arquill.editor.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import consulo.internal.arquill.editor.client.js.JsEditor;
import consulo.internal.arquill.editor.client.js.JsEditorAnnotation;
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

	private Map<Integer, JsEditorAnnotation> myAnnotations = new HashMap<>();

	protected ArquillEventListenerServerRpc myEventProxy;
	protected ArquillEditorConnector myConnector;

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

		if(myJsEditor == null)
		{
			myJsEditor = injectEditor(myId, myConnector.getState().text);
		}
	}

	@Override
	protected void onUnload()
	{
		if(myJsEditor != null)
		{
			myJsEditor.uninstall();
			myJsEditor = null;
		}
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

	public float getLineHeight()
	{
		return myJsEditor != null ? myJsEditor.getLineHeight() : 0;
	}

	public JsEditor getJsEditor()
	{
		return myJsEditor;
	}

	public void addAnnotation(int annotationId, int startOffset, int endOffset, String type, Map<String, String> cssProperties)
	{
		if(myJsEditor == null)
		{
			Window.alert("editor is null");
			return;
		}
		
		JSONObject cssPropertiesJson = new JSONObject();
		for(Map.Entry<String, String> entry : cssProperties.entrySet())
		{
			cssPropertiesJson.put(entry.getKey(), new JSONString(entry.getValue()));
		}

		JsEditorAnnotation annotation = myJsEditor.addAnnotation(startOffset, endOffset, type, cssPropertiesJson.getJavaScriptObject());

		myAnnotations.put(annotationId, annotation);
	}

	public void removeAnnotation(int annotationId)
	{
		if(myJsEditor == null)
		{
			Window.alert("editor is null");
			return;
		}

		JsEditorAnnotation annotation = myAnnotations.remove(annotationId);
		if(annotation != null)
		{
			myJsEditor.removeAnnotation(annotation);
		}
	}

	private static native boolean isLibraryLoad() /*-{
		return $wnd.arquillEditor;
	}-*/;

	private static native JsEditor injectEditor(String id, String text) /*-{
		var editor = $wnd.arquillEditor.createEditor({
			parent: id,
			contents: text
		});

		return editor;
	}-*/;
}
