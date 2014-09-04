package com.android.smap.api.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Column.ForeignKeyAction;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "surveys_contacts")
public class SurveyContact extends Model {
	
	@Column(name = "survey_id", onDelete = ForeignKeyAction.CASCADE)
	public Survey survey;
	
	@Column(name = "contact_id", onDelete = ForeignKeyAction.CASCADE)
	public Contact contact;
	
	/**
	 * Raw XML String to store JavaRosa response  
	 */
	@Column
	private String rawInstance;
	
	// dummy fields for the moment
	public int		answers;
	public int		total;
	public String	updatedAt;
	
	public SurveyContact() {
		
	}
	
	public SurveyContact(Survey survey, Contact contact) {
		this.survey = survey;
		this.contact = contact;
	}
	
	public String getRawInstance() {
		return rawInstance;
	}

	public void setRawInstance(String rawInstance) {
		this.rawInstance = rawInstance;
	}

	public static SurveyContact findBySurveyAndContactIds(long surveyId, long contactId) {

		return new Select()
			.from(SurveyContact.class)
			.where("survey_id = ?", surveyId)
			.where("contact_id = ?", contactId)
			.executeSingle();
	}
}
