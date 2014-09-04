package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.ui.ViewQuery;

public class SettingsFragment extends BaseFragment implements
		OnClickListener {

	private Button	password;
	private Button	username;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_settings,
				null);
		ViewQuery query = new ViewQuery(view);
		query.find(R.id.btn_submit).onClick(this).get();
		username = (Button) query.find(R.id.txt_username).get();
		password = (Button) query.find(R.id.txt_password).get();
		return view;
	}

	@Override
	public void onClick(View arg0) {
		GatewayApp.getPreferenceWrapper().setUserName(
				username.getText().toString());
		GatewayApp.getPreferenceWrapper().setUserName(
				password.getText().toString());
	}

}
