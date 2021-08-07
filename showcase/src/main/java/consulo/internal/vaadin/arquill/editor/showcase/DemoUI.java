package consulo.internal.vaadin.arquill.editor.showcase;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import consulo.internal.arquill.editor.server.ArquillEditor;

import javax.servlet.annotation.WebServlet;

@Theme("valo")
public class DemoUI extends UI
{
	@WebServlet(urlPatterns = "/*", name = "DemoServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = DemoUI.class, productionMode = false)
	public static class DemoServlet extends VaadinServlet
	{
	}

	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		final VerticalLayout layout = new VerticalLayout();
		layout.setHeight("100%");
		layout.setWidth("100%");

		ArquillEditor editor = new ArquillEditor("test");
		editor.addMouseDownListener(event -> new Notification("MouseDown", "Offset: " + event.getTextOffset()).show(getPage()));

		layout.addComponent(editor);

		layout.setExpandRatio(editor, 5);

		editor.setWidth("100%");
		editor.setHeight("100%");

		setContent(layout);
	}
}
