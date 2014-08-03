package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.android.smap.di.DataManager;


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
		
		for(Contact contact : contacts) {
			contact.save();
		}

	}

	@Override
	public void removeContactFromSurvey(int contact, int survey) {
		// TODO Auto-generated method stub
		
	}

	private void seedDevData() {
		
		ActiveAndroid.beginTransaction();
		try {
			for(int n = 1; n <= 10; n++) {
				Survey survey = new Survey("Survey " + n);
				survey.save();
			}
			ActiveAndroid.setTransactionSuccessful();
		} finally {
			ActiveAndroid.endTransaction();
		}
	}
}
