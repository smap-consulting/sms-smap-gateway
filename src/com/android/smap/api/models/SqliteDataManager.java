package com.android.smap.api.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public Survey getSurvey(long id) {

		return Survey.findById((long) id);
	}

	@Override
	public List<Contact> getContacts() {

		return Contact.findAll();
	}

	@Override
	public void addContactsToSurvey(List<Contact> contacts, Survey survey) {
		survey.addContacts(contacts);

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
			
			new Survey("Birds", "parse xml content here").save();
			new Survey("Househould Survey", "parse xml content here").save();
			
			for (int n : IntRange.between(1, 10)) {
				new Contact("Contact " + n, "0123456789").save();
			}

			ActiveAndroid.setTransactionSuccessful();
			
		} finally {

			ActiveAndroid.endTransaction();
		}
	}
	
	public static String convertStreamToString(InputStream is) throws Exception {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    String line = null;
	    while ((line = reader.readLine()) != null) {
	      sb.append(line).append("\n");
	    }
	    reader.close();
	    return sb.toString();
	}

	public static String getStringFromFile (String filePath) throws Exception {
	    File fl = new File(filePath);
	    FileInputStream fin = new FileInputStream(fl);
	    String ret = convertStreamToString(fin);
	    //Make sure you close all streams.
	    fin.close();        
	    return ret;
	}
}
