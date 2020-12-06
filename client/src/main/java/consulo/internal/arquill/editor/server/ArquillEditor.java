package consulo.internal.arquill.editor.server;

import com.google.gwt.user.client.ui.HasText;
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
public class ArquillEditor extends AbstractComponent implements HasText
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

	@Override
	public String getText()
	{
		return getState().text;
	}

	@Override
	public void setText(String text)
	{
		getState().text = text;
	}
}
