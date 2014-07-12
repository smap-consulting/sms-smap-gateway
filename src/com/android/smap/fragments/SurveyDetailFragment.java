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

		mAdapter = new SurveyContactAdapter(getActivity(), mModel.contacts);
		// listView.setAdapter(mAdapter);

		//

		// swipeListView = (SwipeListView) findViewById(R.id.list_contacts);

		swipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
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
		}

		swipeListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
			@Override
			public void onOpened(int position, boolean toRight) {}

			@Override
			public void onClosed(int position, boolean fromRight) {}

			@Override
			public void onListChanged() {}

			@Override
			public void onMove(int position, float x) {}

			@Override
			public void onStartOpen(int position, int action, boolean right) {
				Log.d("swipe", String.format("onStartOpen %d - action %d",
						position, action));
			}

			@Override
			public void onStartClose(int position, boolean right) {
				Log.d("swipe", String.format("onStartClose %d", position));
			}

			@Override
			public void onClickFrontView(int position) {
				Log.d("swipe", String.format("onClickFrontView %d", position));
			}

			@Override
			public void onClickBackView(int position) {
				Log.d("swipe", String.format("onClickBackView %d", position));
			}

			@Override
			public void onDismiss(int[] reverseSortedPositions) {
				for (int position : reverseSortedPositions) {
					mModel.contacts.remove(position);
				}
				mAdapter.notifyDataSetChanged();
			}

		});

		swipeListView.setAdapter(mAdapter);

		return view;
	}

	public int convertDpToPixel(float dp) {
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return (int) px;
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

	//

	// int completed = mModel.survey.completed;
	// int total = mModel.survey.members;
	//
	// String template = getActivity().getResources().getString(
	// R.string.template_quotient);
	// String completedProgress = String.format(template, completed, total);
	// query.find(R.id.txt_completed_progress).text(completedProgress);
	//
	// // grow the progress bar out
	// View progress = query.find(R.id.view_progress).get();
	// float percent = (float) ((float) mModel.survey.completed / (float)
	// mModel.survey.members);
	// MWAnimUtil.growRight(progress, percent);
	//
	// return view;

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(EXTRA_SURVEY_ID, mSurveyId);
	}

}
