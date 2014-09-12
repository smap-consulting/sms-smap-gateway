package com.android.smap.di;

import java.util.List;

import com.android.smap.api.models.Contact;
import com.android.smap.api.models.Survey;

public interface DataManager {

	List<Survey> getSurveys();

	Survey getSurvey(long id);

	List<Contact> getContacts();

	void addContactsToSurvey(List<Contact> contacts, Survey survey);

	void removeContactFromDistribution(long contactId, long distributionId);

	void deleteSurveys(List<Survey> surveys);

	void deleteSurvey(Survey survey);

}
