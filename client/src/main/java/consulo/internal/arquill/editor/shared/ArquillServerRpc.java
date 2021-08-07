package consulo.internal.arquill.editor.shared;

import com.vaadin.shared.communication.ServerRpc;

/**
 * @author VISTALL
 * @since 07/08/2021
 */
public interface ArquillServerRpc extends ServerRpc
{
	void update(float lineHeight);
}
