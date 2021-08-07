package consulo.internal.arquill.editor.server.event;

import com.vaadin.ui.Component;

/**
 * @author VISTALL
 * @since 07/08/2021
 */
public class MouseDownEvent extends Component.Event
{
	private final int myTextOffset;

	public MouseDownEvent(Component source, int textOffset)
	{
		super(source);
		myTextOffset = textOffset;
	}

	public int getTextOffset()
	{
		return myTextOffset;
	}
}
