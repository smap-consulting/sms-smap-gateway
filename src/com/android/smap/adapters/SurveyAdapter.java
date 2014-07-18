package com.android.smap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.android.smap.R;
import com.android.smap.api.models.Survey;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;

public class SurveyAdapter extends VelocAdapter {

	private List<Survey>	mModel;
	private float			mProgressBarTotal;

	@Inject
	public SurveyAdapter(Context context, List<Survey> model) {
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

		int completed = mModel.get((position)).completed;
		int total = mModel.get((position)).members;
		int partial = mModel.get((position)).partial;
		int unfinished = (total - completed) - partial;
		boolean isFinished = completed == total ? true : false;

		// String formatting
		String template = getContext().getResources().getString(
				R.string.template_quotient);
		String totalTemplate = getContext().getResources().getString(
				R.string.surveys_of_total);
		String partialProgress = String.format(template, partial, unfinished);
		String totalCount = String.format(totalTemplate, total);

		query.find(R.id.txt_name).text(mModel.get((position)).name);
		query.find(R.id.txt_completed_progress).text(String.valueOf(completed));
		query.find(R.id.txt_completed_total).text(totalCount);
		query.find(R.id.txt_member_progress).text(partialProgress);

		// set progress bar length
		View progress = query.find(R.id.view_progress).get();
		float percent = (float) ((float) completed / (float) total);
		LayoutParams params = progress.getLayoutParams();
		params.width = (int) (mProgressBarTotal * percent);
		progress.setLayoutParams(params);

		if (isFinished) {
			query.find(R.id.img_tick).invisible(false);
			query.find(R.id.view_progress).invisible(true);
			query.find(R.id.view_progress_total).invisible(true);
		} else {
			query.find(R.id.img_tick).invisible(true);
			query.find(R.id.view_progress).invisible(false);
			query.find(R.id.view_progress_total).invisible(false);
		}
	}

	@Override
	public int getCount() {
		return mModel.size();
	}

	@Override
	public Object getItem(int position) {
		return mModel.get(position);
	}

	public void setModel(List<Survey> model) {
		this.mModel = model;
	}

}
