package consulo.internal.arquill.editor.client;

import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import consulo.internal.arquill.editor.client.js.JsEditor;
import consulo.internal.arquill.editor.server.ArquillEditor;
import consulo.internal.arquill.editor.shared.ArquillClientRpc;
import consulo.internal.arquill.editor.shared.ArquillEditorState;
import consulo.internal.arquill.editor.shared.ArquillEventListenerServerRpc;
import consulo.internal.arquill.editor.shared.ArquillServerRpc;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author VISTALL
 * @since 03/12/2020
 */
@Connect(ArquillEditor.class)
public class ArquillEditorConnector extends AbstractComponentConnector implements ArquillClientRpc
{
	public ArquillEditorConnector()
	{
		registerRpc(ArquillClientRpc.class, this);
	}

	@Override
	public VArquillEditor getWidget()
	{
		return (VArquillEditor) super.getWidget();
	}

	@Override
	protected void init()
	{
		super.init();

		VArquillEditor widget = getWidget();
		widget.myEventProxy = getRpcProxy(ArquillEventListenerServerRpc.class);
		widget.myConnector = this;
	}

	@Override
	public ArquillEditorState getState()
	{
		return (ArquillEditorState) super.getState();
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

		VArquillEditor widget = getWidget();

		Set<String> currentRegisteredEvents = new HashSet<>(widget.getRegisteredEvents());

		Set<String> newEvents = getState().events;

		for(String event : newEvents)
		{
			widget.registerEvent(event);

			currentRegisteredEvents.remove(event);
		}

		// unregister what was left
		for(String event : currentRegisteredEvents)
		{
			widget.unregisterEvent(event);
		}
	}

	@OnStateChange("text")
	private void onTextChanged()
	{
		VArquillEditor widget = getWidget();

		widget.setText(getState().text);
	}

	@Override
	public void queueUpdate()
	{
		VArquillEditor widget = getWidget();

		float lineHeight = widget.getLineHeight();

		getRpcProxy(ArquillServerRpc.class).update(lineHeight);
	}

	@Override
	public void setCaretOffset(int offset)
	{
		VArquillEditor widget = getWidget();

		JsEditor jsEditor = widget.getJsEditor();
		jsEditor.setCaretOffset(offset);
	}

	@Override
	public void addAnnotation(int annotationId, int startOffset, int endOffset, String type, Map<String, String> cssProperties)
	{
		getWidget().addAnnotation(annotationId, startOffset, endOffset, type, cssProperties);
	}

	@Override
	public void removeAnnotation(int annotationId)
	{
		getWidget().removeAnnotation(annotationId);
	}
}
