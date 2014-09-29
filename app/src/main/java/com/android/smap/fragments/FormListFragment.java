package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.activities.FragmentContainerActivity.Builder;
import com.android.smap.adapters.FormListAdapter;
import com.android.smap.api.models.Survey;
import com.android.smap.api.models.FormList.Form;
import com.android.smap.controllers.ControllerError;
import com.android.smap.controllers.ControllerErrorListener;
import com.android.smap.controllers.ControllerListener;
import com.android.smap.controllers.FormListController;
import com.android.smap.controllers.SurveyDefinitionController;
import com.android.smap.di.DataManager;
import com.android.smap.utils.MWUiUtils;
import com.google.inject.Inject;

public class FormListFragment extends BaseFragment implements
		OnItemClickListener,
		ControllerListener,
		ControllerErrorListener {

	@Inject
	private DataManager			mDataManager;
	private FormListAdapter		mAdapter;
	private FormListController	mController;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_form_list,
				null);

		ListView listView = (ListView) view.findViewById(R.id.list_surveys);
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
		mAdapter = new FormListAdapter(getActivity(), null);
		listView.setOnItemClickListener(this);
		listView.setAdapter(mAdapter);
		mController = new FormListController(getActivity(), this, this);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mController.start();
	}

	@Override
	public void onControllerResult() {
		mAdapter.setModel(mController.getModel());
	}

	@Override
	public void onControllerError(ControllerError error) {
		MWUiUtils.showMessagePopup(getActivity(), "Failed to retrieve Surveys");
	}

	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {

		Form form = (Form) mAdapter.getItem(pos);
		SurveyDefinitionController controller = new SurveyDefinitionController(
				getActivity(),
				new ControllerListener() {
					@Override
					public void onControllerResult() {
						
						MWUiUtils.showMessagePopup(getActivity(),
								"Form Retrieved");
						popFragment();
					}
				}, this, form.getUrl());
        controller.start();
	}

    @Override
    public boolean hasActionBarTitle() {
        return true;
    }

    @Override
    public String getActionBarTitle() {
        return getActivity().getResources().getString(R.string.ab_distributions);
    }
}
