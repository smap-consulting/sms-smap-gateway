package com.android.smap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.android.smap.R;
import com.android.smap.api.models.SurveyDefinition;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;

public class SurveyDefinitionAdapter extends VelocAdapter {

	private List<SurveyDefinition>	mModel;
	private float			mProgressBarTotal;

	@Inject
	public SurveyDefinitionAdapter(Context context, List<SurveyDefinition> model) {
		super(context);
		this.mModel = model;
		mProgressBarTotal = getContext()
				.getResources().getDimension(R.dimen.survey_progress_width);
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {
		return inflator.inflate(R.layout.item_survey, null, false);

	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {
		
		SurveyDefinition surveyDef = mModel.get((position));
		
		String surveyDefName = surveyDef.name;
		//TODO
	}

	@Override
	public int getCount() {
		return mModel.size();
	}

	@Override
	public Object getItem(int position) {
		return mModel.get(position);
	}

	public void setModel(List<SurveyDefinition> model) {
		this.mModel = model;
	}

}
