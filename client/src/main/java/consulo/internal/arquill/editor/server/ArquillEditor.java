package consulo.internal.arquill.editor.server;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.ui.AbstractComponent;
import consulo.internal.arquill.editor.shared.AquillEditorState;

/**
 * @author VISTALL
 * @since 03/12/2020
 */
@JavaScript("js/arquillEditor.js")
@StyleSheet("js/arquillEditor.css")
public class ArquillEditor extends AbstractComponent
{
	public ArquillEditor(String text)
	{
		getState().text = text;
	}

	@Override
	protected AquillEditorState getState()
	{
		return (AquillEditorState) super.getState();
	}

	public String getText()
	{
		return getState().text;
	}

	public void setText(String text)
	{
		getState().text = text;
	}
}
