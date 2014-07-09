package com.android.smap.di.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.android.smap.api.models.Survey;
import com.android.smap.di.DataManager;

public class MockDataManager implements DataManager {

	@Override
	public List<Survey> getSurveys() {

		ArrayList<Survey> list = new ArrayList<Survey>();

		Survey s = new Survey();
		s.completed = 2;
		s.members = 10;
		s.name = "Medical Survey: May";
		s.partial = 4;
		s.id = 1;

		Survey s2 = new Survey();
		s2.completed = 10;
		s2.members = 10;
		s2.name = "Medical Survey: April";
		s2.partial = 0;
		s2.id = 1;
		list.add(s);
		list.add(s2);

		Random r = new Random();
		for (int i = 0; i < 3; i++) {
			Survey fake = new Survey();
			fake.completed = r.nextInt(10);
			fake.members = 10;
			fake.name = "Dummy Survey " + i;
			fake.partial = 10 - s.completed;
			fake.id = 1;
			list.add(fake);
		}

		return list;
	}
}
