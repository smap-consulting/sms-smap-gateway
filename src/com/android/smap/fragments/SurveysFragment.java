package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.smap.R;
import com.android.smap.ui.ViewQuery;

public class SurveysFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ListView view = (ListView) inflater.inflate(R.layout.fragment_surveys,
				null);
		ViewQuery query = new ViewQuery(view);

		//view.setAdapter(new SurveyAdapter());
		// setup view

		return view;
	}
}
