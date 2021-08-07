package consulo.internal.arquill.editor.shared;

import com.vaadin.shared.communication.ClientRpc;

/**
 * @author VISTALL
 * @since 07/08/2021
 */
public interface ArquillClientRpc extends ClientRpc
{
	// will send from client info about view
	void queueUpdate();

	void setCaretOffset(int offset);
}
