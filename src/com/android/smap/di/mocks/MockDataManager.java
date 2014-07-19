package com.android.smap.di.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.text.format.DateFormat;

import com.android.smap.api.models.Contact;
import com.android.smap.api.models.SurveyContact;
import com.android.smap.api.models.Survey;
import com.android.smap.api.models.SurveyDetail;
import com.android.smap.di.DataManager;

public class MockDataManager implements DataManager {

	private List<Survey>	mSurveys;
	private List<Contact>	mContacts;
	private SurveyDetail	mSurveyDetail;

	@Override
	public List<Survey> getSurveys() {

		if (mSurveys != null) {
			return mSurveys;
		}

		ArrayList<Survey> list = new ArrayList<Survey>();

		Survey s = new Survey();
		s.completed = 2;
		s.members = 10;
		s.name = "Medical Survey: April";
		s.partial = 4;

		Survey s2 = new Survey();
		s2.completed = 10;
		s2.members = 10;
		s2.name = "Medical Survey: May";
		s2.partial = 0;
		list.add(s);
		list.add(s2);

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			Survey fake = new Survey();
			fake.completed = r.nextInt(10);
			fake.members = 10;
			fake.name = "Dummy Survey " + i;
			fake.partial = 10 - fake.completed;
			list.add(fake);
		}
		mSurveys = list;
		return mSurveys;
	}

	@Override
	public SurveyDetail getDetailsForSurvey(int mSurveyId) {

		if (mSurveyDetail != null) {
			return mSurveyDetail;
		}

		SurveyDetail sd = new SurveyDetail();

		List<SurveyContact> contacts = new ArrayList<SurveyContact>();

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			SurveyContact fake = new SurveyContact();
			fake.name = "Bob #" + i;
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

		mSurveyDetail = sd;
		return mSurveyDetail;
	}

	@Override
	public List<Contact> getContacts() {

		if (mContacts != null) {
			return mContacts;
		}
		List<Contact> contacts = new ArrayList<Contact>();
		Random r = new Random();
		for (int i = 10; i < 20; i++) {
			Contact fake = new Contact();
			fake.name = "Jack #" + i;
			fake.number = "0372 433 2465";
			contacts.add(fake);
		}

		mContacts = contacts;
		return mContacts;
	}

	@Override
	public void putContacts(List<Contact> newContacts) {

		// add to survey
		List<SurveyContact> tempSurveyContacts = new ArrayList<SurveyContact>();

		Random r = new Random();
		for (Contact contact : newContacts) {
			SurveyContact fake = new SurveyContact();
			fake.name = contact.name;
			fake.number = contact.number;
			fake.surveyId = 1;
			fake.answers = r.nextInt(10);
			fake.total = 10;
			fake.updatedAt = (DateFormat.format("dd-MM hh:mm",
					new java.util.Date()).toString());
			tempSurveyContacts.add(fake);
		}

		getDetailsForSurvey(1).contacts.addAll(tempSurveyContacts);
	}

	@Override
	public void removeContactFromSurvey(int contact, int survey) {

		SurveyDetail s = getDetailsForSurvey(1);
		s.contacts.remove(contact);

	}

}
