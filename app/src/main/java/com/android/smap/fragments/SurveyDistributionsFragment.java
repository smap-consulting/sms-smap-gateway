package com.android.smap.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.activities.FragmentContainerActivity.Builder;
import com.android.smap.adapters.DistributionAdapter;
import com.android.smap.adapters.SurveyAdapter;
import com.android.smap.api.models.Distribution;
import com.android.smap.api.models.Survey;
import com.android.smap.di.DataManager;
import com.android.smap.utils.MWUiUtils;
import com.google.inject.Inject;

import java.util.List;

public class SurveyDistributionsFragment extends BaseFragment implements
		OnItemClickListener {

    public static final String		EXTRA_SURVEY_ID	= SurveyDetailFragment.class
            .getCanonicalName()
            + "id";

	private Survey             mSurvey;

    @Inject
	private DataManager		mDataManager;



    // TODO - Create Distribution Adapter
    private DistributionAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_distributions,
				null);
        mDataManager = GatewayApp.getDependencyContainer().getDataManager();
        Bundle b = getArguments();
        if (b != null) {
            mSurvey = mDataManager.getSurvey(b.getLong(EXTRA_SURVEY_ID));
        }
        TextView textView = (TextView) view.findViewById(R.id.txt_survey_name);
        textView.setText(mSurvey.getName());
		ListView listView = (ListView) view.findViewById(R.id.list_distributions);
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
        mAdapter = new DistributionAdapter(getActivity(), mSurvey.getDistributions());
		listView.setOnItemClickListener(this);
		listView.setAdapter(mAdapter);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mAdapter.setModel(mSurvey.getDistributions());
	}

	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {
		Distribution distribution = (Distribution) mAdapter.getItem(pos);
		Bundle b = new Bundle();
		b.putLong(SurveyDetailFragment.EXTRA_SURVEY_ID, distribution.getId());
		startActivity(new Builder(getActivity(), SurveyDetailFragment.class)
				.arguments(b).build());
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.menu_add, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean handled = false;
		switch (item.getItemId()) {
		case android.R.id.home: // Actionbar home/up icon
			getActivity().onBackPressed();
			break;
		case R.id.action_add:
            Bundle b = new Bundle();
            b.putLong(EXTRA_SURVEY_ID, mSurvey.getId());
            startActivity(new Builder(getActivity(), SurveyDistributionCreateFragment.class)
                    .arguments(b).build());
			break;
		}
		return handled;
	}
}
