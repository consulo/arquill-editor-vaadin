package consulo.internal.arquill.editor.server.event;

import com.vaadin.event.SerializableEventListener;
import com.vaadin.util.ReflectTools;

import java.lang.reflect.Method;

/**
 * @author VISTALL
 * @since 07/08/2021
 */
public interface MouseDownListener extends SerializableEventListener
{
	Method METHOD = ReflectTools.findMethod(MouseDownListener.class, "onMouseDown", MouseDownEvent.class);

	void onMouseDown(MouseDownEvent event);
}
