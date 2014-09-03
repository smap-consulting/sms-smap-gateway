package com.android.smap.api.models;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "surveydef")
public class SurveyDefinition extends Model {
	
	@Column
	public String name;
	
	@Column
	public String path;
	
	public SurveyDefinition() {}
	
	public SurveyDefinition(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	public static SurveyDefinition findById(Long id) {
		return Model.load(SurveyDefinition.class, id);
	}
	
	public static List<SurveyDefinition> findAll() {
		return new Select().from(SurveyDefinition.class).execute();
	}
	
}
