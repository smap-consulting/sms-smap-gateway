package com.android.smap.di.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.text.format.DateFormat;

import com.android.smap.api.models.Contact;
import com.android.smap.api.models.SurveyContact;
import com.android.smap.api.models.Survey;
import com.android.smap.di.DataManager;

public class MockDataManager implements DataManager {
	
	private Survey 			mSurvey;
	private List<Survey>	mSurveys;
	private List<Contact>	mContacts;

	@Override
	public List<Survey> getSurveys() {

		if (mSurveys != null) {
			return mSurveys;
		}
		List<Survey> surveys = Survey.findAll();
		if (surveys.size() > 0) {
			return surveys;
		}

		Survey survey1 = new Survey();
//		survey1.completed = 2;
//		survey1.members = 10;
		survey1.name = "Medical Survey: April";
//		survey1.partial = 4;
		survey1.save();

		Survey survey2 = new Survey();
//		survey2.completed = 10;
//		survey2.members = 10;
		survey2.name = "Medical Survey: May";
//		survey2.partial = 0;
		survey2.save();

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			Survey fake = new Survey();
//			fake.completed = r.nextInt(10);
//			fake.members = 10;
			fake.name = "Dummy Survey " + i;
//			fake.partial = 10 - fake.completed;
			fake.save();
		}
		return Survey.findAll();
	}

	@Override
	public Survey getSurvey(long mSurveyId) {

		if (mSurvey != null) {
			return mSurvey;
		}

		Survey survey = new Survey();
		this.mSurvey = survey;

		List<SurveyContact> surveyContacts = new ArrayList<SurveyContact>();

		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			Contact fakeContact = new Contact();
			fakeContact.name = "Bob #" + i;
			fakeContact.number = "0374 233 8475";
			
			SurveyContact surveyContact = new SurveyContact();
			surveyContact.survey = survey;
			surveyContact.contact = fakeContact;

			surveyContact.answers = r.nextInt(10);
			surveyContact.total = 10;
			surveyContact.updatedAt = (DateFormat.format("dd-MM hh:mm",
					new java.util.Date()).toString());
			surveyContacts.add(surveyContact);
		}


		return survey;
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

	
	public void addContactsToSurvey(List<Contact> contacts, Survey survey) {

//		// add to survey
		List<Contact> tempSurveyContacts = new ArrayList<Contact>();
//
		Random r = new Random();
		for (Contact contact : contacts) {
//			Contact fake = new Contact();
//			fake.name = contact.name;
//			fake.number = contact.number;
//			fake.surveyId = 1;
//			fake.answers = r.nextInt(10);
//			fake.total = 10;
//			fake.updatedAt = (DateFormat.format("dd-MM hh:mm",
//					new java.util.Date()).toString());
//			tempSurveyContacts.add(fake);
		}

//		getDetailsForSurvey(1).surveyContacts.addAll(tempSurveyContacts);
	}

	@Override
	public void removeContactFromSurvey(long contact, long survey) {

//		SurveyDetail s = getDetailsForSurvey(1);
//		s.surveyContacts.remove(contact);

	}

}
