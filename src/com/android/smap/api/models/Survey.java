package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "surveys")
public class Survey extends Model {
	
	@Column
	public String name;
	
	public int members;
	public int completed;
	public int partial;
	
	public static Survey findById(Long id) {
		return Model.load(Survey.class, id);
	}
	
	public static List<Survey> findAll() {
		return new Select().from(Survey.class).execute();
	}
}
