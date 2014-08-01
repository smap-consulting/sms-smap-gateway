package com.android.smap.api.models;

import java.util.List;

import com.android.smap.di.DataManager;

public class SqliteDataManager implements DataManager {

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

}
