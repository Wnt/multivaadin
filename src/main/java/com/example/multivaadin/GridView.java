package com.example.multivaadin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "grid-view", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class GridView extends VerticalLayout {
	Faker f = new Faker(new Random(42));

	public GridView() {
		Grid<Dto> grid = new Grid<>(Dto.class);
		ListDataProvider<Dto> dataProvider = new ListDataProvider<Dto>(getDummyData(40));

		grid.setDataProvider(dataProvider);

		Editor<Dto> editor = grid.getEditor();
		editor.setBuffered(true);

		add(grid);
		grid.setSizeFull();
	}

	private Collection<Dto> getDummyData(int count) {
		ArrayList<Dto> list = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			Double low = f.number().randomDouble(2, 0, 120);
			Double high = f.number().randomDouble(2, low.intValue(), 120);
			double mean = f.number().randomDouble(2, low.intValue(), high.intValue());
			List<String> emails = Arrays.asList(f.internet().emailAddress(), f.internet().emailAddress(),
					f.internet().emailAddress());
			list.add(new Dto(f.commerce().productName(), low, mean, high, emails));
		}
		return list;
	}
}
