package com.android.smap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.smap.R;
import com.android.smap.api.models.SurveyContact;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;

public class SurveyContactAdapter extends VelocAdapter {

	private List<SurveyContact>	mModel;

	@Inject
	public SurveyContactAdapter(Context context, List<SurveyContact> model) {
		super(context);
		this.mModel = model;
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {
		return inflator.inflate(R.layout.item_survey_contact_slider, null, false);
	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {

		int completed = mModel.get((position)).answers;
		int total = mModel.get((position)).total;

		String template = getContext().getResources().getString(
				R.string.template_quotient);
		String completedProgress = String.format(template, completed, total);

		query.find(R.id.txt_name).text(mModel.get((position)).name);
		query.find(R.id.txt_number).text("Ph: "+mModel.get((position)).number);
		query.find(R.id.txt_completed_progress).text(completedProgress);
		query.find(R.id.txt_timestamp).text(mModel.get((position)).updatedAt);

	}

	@Override
	public int getCount() {
		return mModel.size();
	}

	@Override
	public Object getItem(int position) {
		return mModel.get(position);
	}

	public void setModel(List<SurveyContact> model) {
		this.mModel = model;
	}

}
