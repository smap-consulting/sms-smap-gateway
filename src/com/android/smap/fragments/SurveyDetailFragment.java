package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.adapters.SurveyContactAdapter;
import com.android.smap.api.models.SurveyDetail;
import com.android.smap.di.DataManager;
import com.android.smap.ui.ViewQuery;
import com.android.smap.utils.MWAnimUtil;
import com.google.inject.Inject;

public class SurveyDetailFragment extends BaseFragment {

	public static final String		EXTRA_SURVEY_ID	= SurveyDetailFragment.class
															.getCanonicalName()
															+ "id";
	@Inject
	private DataManager				mDataManager;
	private SurveyDetail			mModel;
	private SurveyContactAdapter	mAdapter;
	private int						mSurveyId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			mSurveyId = b.getInt(EXTRA_SURVEY_ID);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_survey_detail,
				null);

		ViewQuery query = new ViewQuery(view);
		ListView listView = (ListView) query.find(R.id.list_contacts).get();
		mDataManager = GatewayApp.getDependencyContainer().getInjector()
				.getInstance(DataManager.class);
		mModel = mDataManager.getDetailsForSurvey(mSurveyId);

		mAdapter = new SurveyContactAdapter(getActivity(), mModel.contacts);
		listView.setAdapter(mAdapter);

		View progress = query.find(R.id.view_progress).get();

		float percent = (float) ((float) mModel.survey.completed / (float) mModel.survey.members);

		MWAnimUtil.growRight(progress, percent);

		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(EXTRA_SURVEY_ID, mSurveyId);
	}

}
