package com.android.smap.di.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.text.format.DateFormat;

import com.android.smap.api.models.SurveyContact;
import com.android.smap.api.models.Survey;
import com.android.smap.api.models.SurveyDetail;
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
		for (int i = 0; i < 10; i++) {
			Survey fake = new Survey();
			fake.completed = r.nextInt(10);
			fake.members = 10;
			fake.name = "Dummy Survey " + i;
			fake.partial = 10 - fake.completed;
			fake.id = 1;
			list.add(fake);
		}

		return list;
	}

	@Override
	public SurveyDetail getDetailsForSurvey(int mSurveyId) {

		SurveyDetail sd = new SurveyDetail();

		List<SurveyContact> contacts = new ArrayList<SurveyContact>();

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			SurveyContact fake = new SurveyContact();
			fake.name = "Bob #"+i;
			fake.number = "0374 233 8475";
			fake.surveyId = 1;
			fake.answers = r.nextInt(10);
			fake.total = 10;
			fake.updatedAt = (DateFormat.format("dd-MM hh:mm",
					new java.util.Date()).toString());
			contacts.add(fake);
		}

		sd.contacts = contacts;
		sd.survey = getSurveys().get(0);

		return sd;
	}
}
