package consulo.internal.arquill.editor.shared;

import com.vaadin.shared.AbstractComponentState;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author VISTALL
 * @since 03/12/2020
 */
public class ArquillEditorState extends AbstractComponentState
{
	public String text;

	public Set<String> events = new LinkedHashSet<>();

	public float lineHeight;
}
