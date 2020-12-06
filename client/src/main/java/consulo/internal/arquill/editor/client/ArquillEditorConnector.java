package consulo.internal.arquill.editor.client;

import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import consulo.internal.arquill.editor.server.ArquillEditor;
import consulo.internal.arquill.editor.shared.AquillEditorState;

/**
 * @author VISTALL
 * @since 03/12/2020
 */
@Connect(ArquillEditor.class)
public class ArquillEditorConnector extends AbstractComponentConnector
{
	@Override
	public VAquillEditor getWidget()
	{
		return (VAquillEditor) super.getWidget();
	}

	@Override
	public AquillEditorState getState()
	{
		return (AquillEditorState) super.getState();
	}

//	@Override
//	protected void setWidgetStyleName(String styleName, boolean add)
//	{
//	}
//
//	@Override
//	protected void setWidgetStyleNameWithPrefix(String prefix, String styleName, boolean add)
//	{
//	}
//
//	@Override
//	protected void updateWidgetStyleNames()
//	{
//	}
//
//	@Override
//	public void setWidgetEnabled(boolean widgetEnabled)
//	{
//	}

	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent)
	{
		super.onStateChanged(stateChangeEvent);

		getWidget().setText(getState().text);
	}
}
