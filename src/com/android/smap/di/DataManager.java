package com.android.smap.di;

import java.util.List;

import com.android.smap.api.models.Contact;
import com.android.smap.api.models.Survey;
import com.android.smap.api.models.SurveyDefinition;

public interface DataManager {

	List<Survey> getSurveys();

	Survey getSurvey(long id);
	
	List<SurveyDefinition> getSurveysDef();
	
	SurveyDefinition getSurveyDef(long id);

	List<Contact> getContacts();

	void addContactsToSurvey(List<Contact> contacts, Survey survey);

	void removeContactFromSurvey(long contact, long survey);

}
