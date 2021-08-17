package consulo.internal.arquill.editor.shared;

import com.vaadin.shared.communication.ClientRpc;

import java.util.Map;

/**
 * @author VISTALL
 * @since 07/08/2021
 */
public interface ArquillClientRpc extends ClientRpc
{
	// will send from client info about view
	void queueUpdate();

	void setCaretOffset(int offset);

	void addAnnotation(int annotationId, int startOffset, int endOffset, String type, Map<String, String> cssProperties);

	void removeAnnotation(int annotationId);
}
