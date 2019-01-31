package com.example.multivaadin;

import java.util.UUID;

import com.github.javafaker.Faker;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;

@Push
@HtmlImport("frontend://styles/shared-styles.html")
public class MainLayout extends VerticalLayout implements RouterLayout {
	private FlexLayout viewContainer;
	private H1 h1;
	private String sharedObjectId;

	public MainLayout() {
		h1 = new H1("This is a Vaadin 12 header");
		add(h1);

		Dto sharedObject = new Dto(new Faker().commerce().productName(), 0, 0, 0, null);
		sharedObjectId = UUID.randomUUID().toString();
		VaadinSession.getCurrent().getSession().setAttribute("shared-object-" + sharedObjectId, sharedObject);

		add(new Paragraph("Shared object name: '" + sharedObject.getName() + "'"));

		add(new HorizontalLayout(

				new RouterLink("Landing view", GridView.class),

				new RouterLink("Vaadin 7 view", LegacyVaadinViewWrapper1.class)));

		viewContainer = new FlexLayout();
		viewContainer.setAlignItems(Alignment.STRETCH);
		add(viewContainer);
		expand(viewContainer);
		setHorizontalComponentAlignment(Alignment.STRETCH, viewContainer);

		setSizeFull();

		VaadinSession.getCurrent().getSession().setAttribute("main-view", this);
	}

	@Override
	public void showRouterLayoutContent(HasElement content) {
		viewContainer.getElement().appendChild(content.getElement());
	}

	public void setHeading(String string) {
		System.out.println("flow setHeading");
		getUI().ifPresent(ui -> ui.access(() -> {
			h1.setText(string);
			System.out.println("flow ui access" + string);
		}));
	}

	public String getSharedObjectId() {
		return sharedObjectId;
	}
}