package com.android.smap.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.smap.R;
import com.android.smap.api.models.Survey;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;

public class SurveyAdapter extends VelocAdapter {

	List<Survey>	mModel;

	public SurveyAdapter(Context context) {
		super(context);
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {

		return inflator.inflate(R.layout.item_survey, parent);
	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {

		query.find(R.id.txt_survey).text(mModel.get((position)).name);
		query.find(R.id.txt_survey).text(mModel.get((position)).completed);
		query.find(R.id.txt_survey).text(mModel.get((position)).members);
		query.find(R.id.txt_survey).text(mModel.get((position)).partial);

	}

	@Override
	public int getCount() {
		return mModel.size();
	}

	@Override
	public Object getItem(int position) {
		return mModel.get(position);
	}

}
