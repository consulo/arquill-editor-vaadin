package consulo.internal.arquill.editor.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author VISTALL
 * @since 03/12/2020
 */
public class VAquillEditor extends Widget
{
	private final String myId;
	private JsEditor myJavaScriptObject;

	public VAquillEditor()
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
		myJavaScriptObject.setText(text);
	}

	@Override
	protected void onLoad()
	{
		if(!isLibraryLoad())
		{
			Window.alert("ArquillEditor not loaded");
		}
		myJavaScriptObject = injectEditor(myId);
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
