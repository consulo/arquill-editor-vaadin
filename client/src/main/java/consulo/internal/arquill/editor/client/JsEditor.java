package consulo.internal.arquill.editor.client;

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

	public native void setText(String text) /*-{
		this.setText(text);
	}-*/;
}
