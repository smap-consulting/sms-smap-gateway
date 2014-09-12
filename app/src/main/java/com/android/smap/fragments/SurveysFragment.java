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

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.activities.FragmentContainerActivity.Builder;
import com.android.smap.adapters.SurveyAdapter;
import com.android.smap.api.models.Survey;
import com.android.smap.di.DataManager;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;
import com.mjw.android.swipe.MultiChoiceSwipeListener;
import com.mjw.android.swipe.SwipeListView;

import java.util.ArrayList;
import java.util.List;

public class SurveysFragment extends BaseFragment {

	@Inject
	private DataManager		mDataManager;
	private List<Survey>	mModel;
	private SurveyAdapter	mAdapter;
	private SwipeListView	mSwipeListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_surveys,
				null);
		
		ViewQuery query = new ViewQuery(view);
		mSwipeListView = (SwipeListView) query.find(R.id.list_surveys).get();
		
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
		mModel = mDataManager.getSurveys();
		setupSurveysList();

		return view;
	}
	
	private void setupSurveysList() {

		mAdapter = new SurveyAdapter(getActivity(), mModel,
				mSwipeListView);
		mSwipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		mSwipeListView
				.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

					@Override
					public void onItemCheckedStateChanged(ActionMode mode,
							int position,
							long id, boolean checked) {
						mode.setTitle("Remove ("
								+ mSwipeListView.getCountSelected()+ ")");
					}

					@Override
					public boolean onActionItemClicked(ActionMode mode,
							MenuItem item) {
						switch (item.getItemId()) {
						case R.id.menu_delete_survey:
							//removeSurveys();
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
						inflater.inflate(R.menu.menu_delete, menu);
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
		
		mSwipeListView.setSwipeListViewListener(new MultiChoiceSwipeListener(mAdapter) {
		    @Override
		    public void onClickFrontView(int position) {
		         super.onClickFrontView(position);
		 		Survey survey = (Survey) mAdapter.getItem(position);

				Bundle b = new Bundle();
				b.putLong(SurveyDetailFragment.EXTRA_SURVEY_ID, survey.getId());
				startActivity(new Builder(getActivity(), SurveyDetailFragment.class)
						.arguments(b).build());
		         
		    }
		});
		
		mSwipeListView.setAdapter(mAdapter);

	}
	
	@Override
	public void onResume() {
		super.onResume();
		mAdapter.setModel(mDataManager.getSurveys());
	}

	
	private void removeSurveys() {

		List<Integer> selected = mSwipeListView.getPositionsSelected();
		List<Survey> surveys = new ArrayList<Survey>();
		for (Integer i : selected) {
			surveys.add(mModel.get(i));
		}
		mDataManager.deleteSurveys(surveys);

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
			pushFragment(FormListFragment.class);
			break;
		}
		return handled;

	}

}
