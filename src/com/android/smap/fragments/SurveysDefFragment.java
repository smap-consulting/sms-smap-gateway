package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.activities.FragmentContainerActivity.Builder;
import com.android.smap.adapters.SurveyDefAdapter;
import com.android.smap.api.models.Survey;
import com.android.smap.api.models.SurveyDefinition;
import com.android.smap.di.DataManager;
import com.google.inject.Inject;

public class SurveysDefFragment extends BaseFragment implements
	OnItemClickListener {
	
	@Inject
	private DataManager		mDataManager;
	private SurveyDefAdapter	mAdapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	
	LinearLayout view = (LinearLayout) inflater.inflate(
			R.layout.fragment_surveysdef,null);
	
	ListView listView = (ListView) view.findViewById(R.id.list_surveysdef);
	
	mDataManager = GatewayApp.getDependencyContainer().getDataManager();
	mAdapter = new SurveyDefAdapter(getActivity(), mDataManager
			.getSurveysDef());
	listView.setOnItemClickListener(this);
	listView.setAdapter(mAdapter);
	
	return view;
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {
	//TODO
	/*
	SurveyDefinition surveydef = (SurveyDefinition) mAdapter.getItem(pos);
	
	Bundle b = new Bundle();
	b.putLong(SurveyDefDetailFragment.EXTRA_SURVEYDEF_ID, surveydef.getId());
	startActivity(new Builder(getActivity(), SurveyDetailFragment.class)
			.arguments(b).build());
	*/
	}
	}