package consulo.internal.arquill.editor.client.js;

import consulo.internal.arquill.editor.shared.ArquillEventName;
import consulo.internal.arquill.editor.shared.ArquillEventListenerServerRpc;

import java.util.HashMap;
import java.util.Map;

/**
 * @author VISTALL
 * @since 07/08/2021
 */
public abstract class JsEventProcessor
{
	public static Map<String, JsEventProcessor> ourProcessors = new HashMap<>();

	static
	{
		ourProcessors.put(ArquillEventName.MouseDown, new JsEventProcessor()
		{
			@Override
			public void call(JsEditor editor, JsEvent event, ArquillEventListenerServerRpc rpc)
			{
				int offset = editor.getOffset(event.getX(), event.getY());
				rpc.onMouseDown(offset);
			}
		});
	}

	public abstract void call(JsEditor editor, JsEvent event, ArquillEventListenerServerRpc rpc);
}
