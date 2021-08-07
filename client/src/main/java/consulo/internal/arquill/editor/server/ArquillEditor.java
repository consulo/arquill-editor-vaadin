package consulo.internal.arquill.editor.server;

import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.shared.Registration;
import com.vaadin.ui.AbstractComponent;
import consulo.internal.arquill.editor.server.event.MouseDownEvent;
import consulo.internal.arquill.editor.server.event.MouseDownListener;
import consulo.internal.arquill.editor.shared.ArquillEventName;
import consulo.internal.arquill.editor.shared.ArquillEditorState;
import consulo.internal.arquill.editor.shared.ArquillEventListenerServerRpc;

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

		registerRpc(new ArquillEventListenerServerRpc()
		{
			@Override
			public void onMouseDown(int textOffset)
			{
				fireEvent(new MouseDownEvent(ArquillEditor.this, textOffset));
			}
		}, ArquillEventListenerServerRpc.class);
	}

	public Registration addMouseDownListener(MouseDownListener listener)
	{
		getState().events.add(ArquillEventName.MouseDown);

		Registration registration = addListener(MouseDownEvent.class, listener, MouseDownListener.METHOD);
		return () -> {
			registration.remove();
			if(!hasListeners(MouseDownEvent.class))
			{
				getState().events.remove(ArquillEventName.MouseDown);
			}
		};
	}

	@Override
	protected ArquillEditorState getState()
	{
		return (ArquillEditorState) super.getState();
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
