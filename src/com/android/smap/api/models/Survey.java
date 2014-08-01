package com.android.smap.api.models;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "surveys")
public class Survey extends Model {
	
	@Column
	public String name;
	
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
//		List<SurveyContact> contacts = getMany(SurveyContact.class, "survey_id");
		return new ArrayList<SurveyContact>();
	}
}
