package com.android.smap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.api.models.Contact;
import com.android.smap.api.models.SurveyContact;
import com.android.smap.di.DataManager;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;
import com.mjw.android.swipe.MultiChoiceSwipeListener.MultiSelectActionAdapter;
import com.mjw.android.swipe.SwipeListView;

public class SurveyContactAdapter extends VelocAdapter implements
		MultiSelectActionAdapter {

	private List<SurveyContact> mModel;
	private SwipeListView mListViewRef;
	private DataManager mDataManager;

	@Inject
	public SurveyContactAdapter(Context context, List<SurveyContact> model,
			SwipeListView ref) {
		super(context);
		this.mModel = model;
		this.mListViewRef = ref;
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
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

		SurveyContact surveyContact = getItem(position);

		Contact contact;
		String contactName, phoneNumber;
		if ((contact = surveyContact.contact) != null) {
			contactName = contact.getName();
			phoneNumber = contact.getNumber();
		} else {
			contactName = "";
			phoneNumber = "";
		}

		String updatedAt = surveyContact.updatedAt;
		int completed = surveyContact.answers;
		int total = surveyContact.total;

		String template = getContext().getResources().getString(
				R.string.surveys_of_total);
		String totalCount = String.format(template, total);

		query.find(R.id.txt_name).text(contactName);
		query.find(R.id.txt_number).text("Ph: " + phoneNumber);
		query.find(R.id.txt_completed_progress).text(String.valueOf(completed));
		query.find(R.id.txt_completed_total).text(totalCount);
		query.find(R.id.txt_timestamp).text(updatedAt);

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
		SurveyContact surveyContact = getItem(pos);
		mDataManager.removeContactFromDistribution(
                surveyContact.contact.getId(),
                surveyContact.getDistribution().getId());
        mModel.remove(pos);
		notifyDataSetChanged();

	}

}
