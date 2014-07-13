package com.android.smap.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.adapters.SurveyContactAdapter;
import com.android.smap.api.models.SurveyDetail;
import com.android.smap.di.DataManager;
import com.android.smap.ui.ViewQuery;
import com.android.smap.utils.MWAnimUtil;
import com.google.inject.Inject;
import com.mjw.android.swipe.BaseSwipeListViewListener;
import com.mjw.android.swipe.SwipeListView;

public class SurveyDetailFragment extends BaseFragment {

	public static final String		EXTRA_SURVEY_ID	= SurveyDetailFragment.class
															.getCanonicalName()
															+ "id";
	@Inject
	private DataManager				mDataManager;
	private SurveyDetail			mModel;
	private SurveyContactAdapter	mAdapter;
	private int						mSurveyId;
	private SwipeListView			swipeListView;
	private View					mProgressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			mSurveyId = b.getInt(EXTRA_SURVEY_ID);
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_survey_detail,
				null);

		ViewQuery query = new ViewQuery(view);
		swipeListView = (SwipeListView) query.find(R.id.list_contacts).get();

		// get all necessary local data
		mDataManager = GatewayApp.getDependencyContainer().getInjector()
				.getInstance(DataManager.class);
		mModel = mDataManager.getDetailsForSurvey(mSurveyId);
		setupContactsList();

		int completed = mModel.survey.completed;
		int total = mModel.survey.members;

		String template = getActivity().getResources().getString(
				R.string.template_quotient);
		String completedProgress = String.format(template, completed, total);
		query.find(R.id.txt_completed_progress).text(completedProgress);

		// grow the progress bar out
		mProgressBar = query.find(R.id.view_progress).get();

		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mModel != null) {
			float percent = (float) ((float) mModel.survey.completed / (float)
					mModel.survey.members);
			MWAnimUtil.growRight(mProgressBar, percent);
		}
	}

	private void setupContactsList() {

		mAdapter = new SurveyContactAdapter(getActivity(), mModel.contacts);
		swipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		swipeListView
				.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

					@Override
					public void onItemCheckedStateChanged(ActionMode mode,
							int position,
							long id, boolean checked) {
						mode.setTitle("Selected ("
								+ swipeListView.getCountSelected()
								+ ")");
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode,
							MenuItem item) {
						// switch (item.getItemId()) {
						// case R.id.menu_delete:
						// swipeListView.dismissSelected();
						// mode.finish();
						// return true;
						// default:
						// return false;
						// }
						return false;
					}

					@Override
					public boolean onCreateActionMode(ActionMode mode,
							Menu menu) {
						MenuInflater inflater = mode.getMenuInflater();
						inflater.inflate(R.menu.menu_choice_items, menu);
						return true;
					}

					@Override
					public void onDestroyActionMode(ActionMode mode) {
						swipeListView.unselectedChoiceStates();
					}

					@Override
					public boolean onPrepareActionMode(ActionMode mode,
							Menu menu) {
						return false;
					}
				});

		swipeListView.setAdapter(mAdapter);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.menu_app, menu);
		return true;
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		boolean handled = false;
		switch (item.getItemId()) {
		case android.R.id.home: // Actionbar home/up icon

			break;
		}
		return handled;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(EXTRA_SURVEY_ID, mSurveyId);
	}

}
