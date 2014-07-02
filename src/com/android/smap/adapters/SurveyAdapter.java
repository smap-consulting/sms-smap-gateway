package com.android.smap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.smap.R;
import com.android.smap.ui.VelocAdapter;
import com.android.smap.ui.ViewQuery;

public class SurveyAdapter extends VelocAdapter {

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
		// TODO Auto-generated method stub

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
