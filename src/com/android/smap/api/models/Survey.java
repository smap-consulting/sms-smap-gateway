package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "surveys")
public class Survey extends Model {
	
	@Column
	private String name;
	
	/**
	 * Raw XML string with JavaRosa form definition
	 */
	@Column
	private String formContent;
	
	public Survey() {
		
	}
	
	public Survey(String name) {
		this.setName(name);
	}

	public Survey(String name, String formContent) {
		this.name = name;
		this.formContent = formContent;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFormContent() {
		return formContent;
	}

	public void setFormContent(String content) {
		this.formContent = content;
	}

	public int getMembersCount() {
		return getSurveyContacts().size();
	}
	
	public int getCompletedCount() {
		// need to filter by completed
		return getSurveyContacts().size();
	}
	
	public int getPartialCount() {
		// need to filter by partially completed
		return getSurveyContacts().size();
	}
	
	public float getCompletionPercentage() {
		return ((float) getPartialCount() / getCompletedCount()) * 100f;
	}
	
	public static Survey findById(Long id) {
		return Model.load(Survey.class, id);
	}
	
	public static List<Survey> findAll() {
		return new Select().from(Survey.class).execute();
	}
	
	public List<SurveyContact> getSurveyContacts() {
		return getMany(SurveyContact.class, "survey_id");
	}

	public void addContacts(List<Contact> contacts) {
		ActiveAndroid.beginTransaction();
		try {
			
			for (Contact contact : contacts) {
				new SurveyContact(this, contact).save();
			}
			
			ActiveAndroid.setTransactionSuccessful();
		} finally {
			ActiveAndroid.endTransaction();
		}		
	}
}
