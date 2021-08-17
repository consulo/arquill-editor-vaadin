package consulo.internal.vaadin.arquill.editor.showcase;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import consulo.internal.arquill.editor.server.ArquillEditor;

import javax.servlet.annotation.WebServlet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

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

		ArquillEditor editor = new ArquillEditor("test 123456");
		editor.addAttachListener(event -> {
			editor.setCaretOffset(3);

			Map<String, String> csses = new HashMap<>();
			csses.put("color", "blue");
			csses.put("fontWeight", "bold");
			csses.put("textDecoration", "underline");

			int annotationId = editor.addAnnotation(0, 4, "orion.annotation.info", csses);

			ForkJoinPool.commonPool().execute(() -> {
				try
				{
					Thread.sleep(10000L);
				}
				catch(InterruptedException e)
				{
				}

				editor.removeAnnotation(annotationId);
			});
		});

		editor.addMouseDownListener(event -> new Notification("MouseDown", "Offset: " + event.getTextOffset()).show(getPage()));

		layout.addComponent(editor);

		layout.setExpandRatio(editor, 5);

		editor.setWidth("100%");
		editor.setHeight("100%");

		//		Button getLineHeight = new Button("Get Line Height", (e) -> {
		//			new Notification("GetLineHeight", "Line Height: " + editor.getLineHeight()).show(getPage());
		//		});
		//		layout.addComponent(getLineHeight);

		setContent(layout);
	}
}
