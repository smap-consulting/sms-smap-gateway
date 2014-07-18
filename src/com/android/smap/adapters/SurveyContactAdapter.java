package com.android.smap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.api.models.SurveyContact;
import com.android.smap.di.DataManager;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;
import com.mjw.android.swipe.MultiChoiceSwipeListener.MultiSelectActionAdapter;
import com.mjw.android.swipe.SwipeListView;

public class SurveyContactAdapter extends VelocAdapter implements
		MultiSelectActionAdapter {

	private List<SurveyContact>	mModel;
	private SwipeListView		mListViewRef;

	@Inject
	public SurveyContactAdapter(Context context,
			List<SurveyContact> model,
			SwipeListView ref) {
		super(context);
		this.mModel = model;
		this.mListViewRef = ref;
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {
		return inflator.inflate(R.layout.item_survey_contact_slider, null,
				false);
	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {

		// clean up choice selections when scrolling
		mListViewRef.recycle(view, position);

		int completed = mModel.get((position)).answers;
		int total = mModel.get((position)).total;

		String template = getContext().getResources().getString(
				R.string.surveys_of_total);
		String totalCount = String.format(template, total);

		query.find(R.id.txt_name).text(mModel.get((position)).name);
		query.find(R.id.txt_number)
				.text("Ph: " + mModel.get((position)).number);
		query.find(R.id.txt_completed_progress).text(String.valueOf(completed));
		query.find(R.id.txt_completed_total).text(totalCount);
		query.find(R.id.txt_timestamp).text(mModel.get((position)).updatedAt);

	}

	@Override
	public int getCount() {
		return mModel.size();
	}

	@Override
	public SurveyContact getItem(int position) {
		return mModel.get(position);
	}

	public void setModel(List<SurveyContact> model) {
		this.mModel = model;
		notifyDataSetChanged();
	}

	@Override
	public void actionAllSelected(int[] pos) {

		for (int i : pos) {
			this.action(i);
		}
	}

	@Override
	public void action(int pos) {
		mModel.remove(pos);
		GatewayApp.getDependencyContainer().getDataManager()
				.removeContactFromSurvey(pos, 1);
		notifyDataSetChanged();

	}

}
