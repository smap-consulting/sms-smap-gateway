package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "contacts")
public class Contact extends Model {

	@Column
	private String number;

	@Column
	private String name;

	@Column
	private int numberOfQuestions;
	
	
	public Contact() {
		
	}
	
	public Contact(String name, String number) {
		this.name = name;;
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public static Contact findById(Long id) {
		return Model.load(Contact.class, id);
	}
	
	public static List<Contact> findAll() {
		return new Select().from(Contact.class).execute(); 
	}
	
	public List<SurveyContact> getSurveyContacts() {
		return getMany(SurveyContact.class, "contact_id");
	}
	
}
