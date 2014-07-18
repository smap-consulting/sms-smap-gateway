package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.activities.FragmentContainerActivity.Builder;
import com.android.smap.adapters.SurveyAdapter;
import com.android.smap.api.models.Survey;
import com.android.smap.di.DataManager;
import com.google.inject.Inject;

public class SurveysFragment extends BaseFragment implements
		OnItemClickListener {

	@Inject
	private DataManager		mDataManager;
	private SurveyAdapter	mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_surveys,
				null);

		ListView listView = (ListView) view.findViewById(R.id.list_surveys);
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
		mAdapter = new SurveyAdapter(getActivity(), mDataManager
				.getSurveys());
		listView.setOnItemClickListener(this);
		listView.setAdapter(mAdapter);
		return view;
	}

	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {

		Bundle b = new Bundle();
		int id = ((Survey) mAdapter.getItem(pos)).id;
		b.putInt(SurveyDetailFragment.EXTRA_SURVEY_ID, id);
		startActivity(new Builder(getActivity(), SurveyDetailFragment.class)
				.arguments(b).build());

	}
}
