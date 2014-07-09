package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.smap.R;
import com.android.smap.adapters.SurveyAdapter;
import com.android.smap.api.models.Survey;
import com.android.smap.di.DataManager;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;

public class SurveysFragment extends BaseFragment implements
		OnItemClickListener {

	@Inject
	private DataManager		mDataManager;
	private SurveyAdapter	mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ListView view = (ListView) inflater.inflate(R.layout.fragment_surveys,
				null);
		ViewQuery query = new ViewQuery(view);
		mAdapter = new SurveyAdapter(getActivity(), mDataManager
				.getSurveys());
		view.setAdapter(mAdapter);
		view.setOnItemClickListener(this);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {
		Bundle b = new Bundle();
		int id = ((Survey) mAdapter.getItem(pos)).id;
		b.putInt(SurveyDetailFragment.EXTRA_SURVEY_ID, id);
		pushFragment(SurveyDetailFragment.class, b);

	}
}
