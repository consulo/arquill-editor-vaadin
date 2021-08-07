package consulo.internal.arquill.editor.client.js;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * @author VISTALL
 * @since 06/08/2021
 */
public final class JsEvent extends JavaScriptObject
{
	protected JsEvent()
	{
	}

	public native int getX()/*-{
		return this.x;
	}-*/;

	public native int getY()/*-{
		return this.y;
	}-*/;
}
