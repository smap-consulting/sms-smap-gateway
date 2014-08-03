package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.android.smap.di.DataManager;
import com.android.smap.utils.IntRange;

public class SqliteDataManager implements DataManager {

	public SqliteDataManager() {

		if (getSurveys().isEmpty()) {
			seedDevData();
		}
	}

	@Override
	public List<Survey> getSurveys() {

		return Survey.findAll();
	}

	@Override
	public Survey getDetailsForSurvey(int id) {

		return Survey.findById((long) id);
	}

	@Override
	public List<Contact> getContacts() {

		return Contact.findAll();
	}

	@Override
	public void putContacts(List<Contact> contacts) {

		for (Contact contact : contacts) {
			contact.save();
		}

	}

	@Override
	public void removeContactFromSurvey(long contactId, long surveyId) {

		SurveyContact surveyContact = SurveyContact.findBySurveyAndContactIds(
				surveyId, contactId);

		if (surveyContact != null) {
			surveyContact.delete();
		}
	}

	private void seedDevData() {

		ActiveAndroid.beginTransaction();

		try {
			
			for (int n : IntRange.between(1, 10)) {
				new Survey("Survey " + n).save();
			}

			for (int n : IntRange.between(1, 10)) {
				new Contact("Contact " + n, "0123456789").save();
			}

			ActiveAndroid.setTransactionSuccessful();
			
		} finally {

			ActiveAndroid.endTransaction();
		}
	}
}
