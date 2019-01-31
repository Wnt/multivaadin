package com.example.multivaadin;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
public class LegacyVaadinUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		String sharedObjectId = vaadinRequest.getParameter("id");
		Dto sharedObject = (Dto) VaadinSession.getCurrent().getSession()
				.getAttribute("shared-object-" + sharedObjectId);
		final VerticalLayout layout = new VerticalLayout();

		final TextField name = new TextField();
		name.setCaption("Type your name here:");

		Button button = new Button("Click Me");
		button.addClickListener(e -> {
			layout.addComponent(new Label("Thanks " + name.getValue() + ", it works!"));
		});

		layout.addComponents(new Label("Shared object product name: " + sharedObject.getName()), name, button,
				new Button("Change Vaadin 12 header", click -> {

					MainLayout mainLayout = ((MainLayout) VaadinSession.getCurrent().getSession()
							.getAttribute("main-view"));

					mainLayout.setHeading("Interaction from legacy Vaadin -> Vaadin 12");
				}));
		layout.setMargin(true);
		layout.setSpacing(true);

		setContent(layout);
	}

	@WebServlet(urlPatterns = { "/legacy-vaadin/*", "/VAADIN/*" }, name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = LegacyVaadinUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
