package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "contacts")
public class Contact extends Model {

	@Column
	public String number;

	@Column
	public String name;
	
	public static Contact findById(Long id) {
		return Model.load(Contact.class, id);
	}
	
	public static List<Contact> findAll() {
		return new Select().from(Contact.class).execute(); 
	}

}
