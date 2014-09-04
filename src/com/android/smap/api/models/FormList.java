package com.android.smap.api.models;

import java.util.List;

import com.activeandroid.Model;

public class FormList extends Model {

	private List<Form>	forms;

	public static class Form {

		String	name;
		String	url;

		public Form(String name, String url) {
			this.name = name;
			this.url = url;
		}

	}

	public List<Form> getForms() {
		return forms;
	}

	public void setForms(List<Form> forms) {
		this.forms = forms;
	}

}
