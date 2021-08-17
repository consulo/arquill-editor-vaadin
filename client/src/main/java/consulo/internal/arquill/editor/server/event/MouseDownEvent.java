package consulo.internal.arquill.editor.server.event;

import com.vaadin.ui.Component;

/**
 * @author VISTALL
 * @since 07/08/2021
 */
public class MouseDownEvent extends Component.Event
{
	private final int myTextOffset;
	private final int myButton;
	private final int myX;
	private final int myY;

	public MouseDownEvent(Component source, int textOffset, int button, int x, int y)
	{
		super(source);
		myTextOffset = textOffset;
		myButton = button;
		myX = x;
		myY = y;
	}

	public int getButton()
	{
		return myButton;
	}

	public int getX()
	{
		return myX;
	}

	public int getY()
	{
		return myY;
	}

	public int getTextOffset()
	{
		return myTextOffset;
	}
}
