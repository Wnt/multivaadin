package com.example.multivaadin;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;

@Tag("iframe")
public class Iframe extends HtmlComponent {
	public Iframe() {
	}

	public Iframe(String resource) {
		setSource(resource);
	}

	public void setSource(String resource) {
		getElement().setAttribute("src", resource);
	}
}