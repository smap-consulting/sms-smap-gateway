package com.android.smap.di;

import java.util.List;

import com.android.smap.api.models.Contact;
import com.android.smap.api.models.Survey;

public interface DataManager {

	List<Survey> getSurveys();

	Survey getDetailsForSurvey(int id);

	List<Contact> getContacts();

	void putContacts(List<Contact> contacts);

	void removeContactFromSurvey(long contact, long survey);

}
