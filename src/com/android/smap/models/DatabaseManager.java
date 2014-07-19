/**
 * Concrete implementation of DataManager that interacts with the local
 * sqlite database via ActiveAndroid
 */
package com.android.smap.models;

import java.util.List;

import com.android.smap.api.models.Contact;
import com.android.smap.api.models.Survey;
import com.android.smap.api.models.SurveyDetail;
import com.android.smap.di.DataManager;

/**
 * @author christopheratkins
 *
 */
public class DatabaseManager implements DataManager {

	@Override
	public List<Survey> getSurveys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SurveyDetail getDetailsForSurvey(int mSurveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contact> getContacts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putContacts(List<Contact> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeContactFromSurvey(int contact, int survey) {
		// TODO Auto-generated method stub

	}

}
