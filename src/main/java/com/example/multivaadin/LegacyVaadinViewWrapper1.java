package com.example.multivaadin;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.router.Route;

@Route(value = "legacy-vaadin-view-1", layout = MainLayout.class)
public class LegacyVaadinViewWrapper1 extends Composite<Iframe> {
	public LegacyVaadinViewWrapper1() {
		getContent().setWidth("100%");

	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		getParent().ifPresent(parent -> {
			// hacky way to get a reference to an object defined on the main layout level
			MainLayout ml = (MainLayout) parent.getParent().get();
			getContent().setSource("/legacy-vaadin/?id=" + ml.getSharedObjectId());
		});
	}

}
