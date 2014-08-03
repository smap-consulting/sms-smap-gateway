package com.android.smap.fragments;

import android.os.Bundle;
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
import android.widget.Toast;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.adapters.SurveyContactAdapter;
import com.android.smap.api.models.Survey;
import com.android.smap.di.DataManager;
import com.android.smap.ui.ViewQuery;
import com.android.smap.utils.MWAnimUtil;
import com.google.inject.Inject;
import com.mjw.android.swipe.MultiChoiceSwipeListener;
import com.mjw.android.swipe.SwipeListView;

public class SurveyDetailFragment extends BaseFragment {

	public static final String		EXTRA_SURVEY_ID	= SurveyDetailFragment.class
															.getCanonicalName()
															+ "id";
	@Inject
	private DataManager				mDataManager;
	private Survey					mModel;
	private SurveyContactAdapter	mAdapter;
	private int						mSurveyId;
	private SwipeListView			mSwipeListView;
	private View					mProgressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getArguments();
		if (b != null) {
			mSurveyId = (int) b.getLong(EXTRA_SURVEY_ID);
		}
		// get all necessary local data
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
		mModel = mDataManager.getSurvey(mSurveyId);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_survey_detail,
				null);

		ViewQuery query = new ViewQuery(view);
		mSwipeListView = (SwipeListView) query.find(R.id.list_contacts).get();

		setupContactsList();

		int completed = mModel.getCompletedCount();
		int total = mModel.getMembersCount();

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
		
		mModel = mDataManager.getSurvey(mSurveyId);
		mAdapter.setModel(mModel.getSurveyContacts());

		if (mModel != null) {
			float percent = mModel.getCompletionPercentage();
			MWAnimUtil.growRight(mProgressBar, percent);
		}
	}

	private void setupContactsList() {

		mAdapter = new SurveyContactAdapter(getActivity(), mModel.getSurveyContacts(),
				mSwipeListView);
		mSwipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		mSwipeListView
				.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

					@Override
					public void onItemCheckedStateChanged(ActionMode mode,
							int position,
							long id, boolean checked) {
						mode.setTitle("Remove ("
								+ mSwipeListView.getCountSelected()
								+ ")");
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode,
							MenuItem item) {
						switch (item.getItemId()) {
						case R.id.menu_delete:
							
							mSwipeListView.dismissSelected();
							mode.finish();
							return true;
						default:
							return false;
						}

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
						mSwipeListView.unselectedChoiceStates();
					}

					@Override
					public boolean onPrepareActionMode(ActionMode mode,
							Menu menu) {
						return false;
					}
				});

		mSwipeListView.setSwipeListViewListener(new MultiChoiceSwipeListener(
				mAdapter));
		mSwipeListView.setAdapter(mAdapter);

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
		case R.id.action_add: // Actionbar home/up icon
			Bundle b = new Bundle();
			b.putInt(ContactSelectFragment.EXTRA_SURVEY_ID, mSurveyId);
			pushFragment(ContactSelectFragment.class, b);
			Toast.makeText(getActivity(), "ADD CONTACT", Toast.LENGTH_LONG)
					.show();
			break;
		}
		return handled;

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(EXTRA_SURVEY_ID, mSurveyId);
	}

	@Override
	public boolean hasActionBarTitle() {
		return true;
	}

	@Override
	public String getActionBarTitle() {
		return getResources().getString(R.string.ab_survey_contacts);
	}
}
