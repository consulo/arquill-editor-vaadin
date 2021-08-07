package consulo.internal.arquill.editor.shared;

import com.vaadin.shared.communication.ServerRpc;

/**
 * @author VISTALL
 * @since 06/08/2021
 */
public interface ArquillEventListenerServerRpc extends ServerRpc
{
	void onMouseDown(int textOffset);
}
