package consulo.internal.arquill.editor.client.js;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author VISTALL
 * @since 06/12/2020
 */
public final class JsEditor extends JavaScriptObject
{
	protected JsEditor()
	{
	}

	public native JavaScriptObject addListener(String name, JsEventConsumer eventConsumer) /*-{
		var textView = this.getTextView();
		var listener = function(e)
		{
			eventConsumer.@consulo.internal.arquill.editor.client.js.JsEventConsumer::consume(Lconsulo/internal/arquill/editor/client/js/JsEvent;)(e);
		};

		textView.addEventListener(name, listener);
		return listener;
	}-*/;

	public native void removeListener(String name, JavaScriptObject listener)  /*-{
		var textView = this.getTextView();

		textView.removeEventListener(name, listener);
	}-*/;

	public native void setText(String text) /*-{
		this.setText(text);
	}-*/;

	public native int getOffset(int x, int y) /*-{
		return this.getTextView().getOffsetAtLocation(x, y);
	}-*/;
}
