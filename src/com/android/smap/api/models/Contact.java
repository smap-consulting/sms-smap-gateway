package com.android.smap.api.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "contacts")
public class Contact extends Model {

	@Column
	public String	number;

	@Column
	public String	name;

}
