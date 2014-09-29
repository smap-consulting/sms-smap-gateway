package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.smap.R;
import com.android.smap.api.models.Survey;
import com.android.smap.ui.ViewQuery;
import com.android.smap.utils.MWUiUtils;

public class SurveyDistributionCreateFragment extends BaseFragment implements
		OnClickListener {

    public static final String		EXTRA_SURVEY_ID	= DistributionDetailFragment.class
            .getCanonicalName()
            + "id";

	private EditText	name;
    private long			mSurveyId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        Bundle b = getArguments();
        if (b != null) {
            mSurveyId = b.getLong(EXTRA_SURVEY_ID);
        }

		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_distribution_create,
				null);
		ViewQuery query = new ViewQuery(view);
		query.find(R.id.btn_submit).onClick(this).get();
	    name = (EditText) query.find(R.id.txt_name).get();
		return view;
	}

	@Override
	public void onClick(View arg0) {
        //Switch over to "create distribution" with name
        Survey survey = Survey.findById(mSurveyId);

        survey.createDistribution(name.getText().toString());
        survey.save();

		MWUiUtils.hideKeyboard(getActivity());
		MWUiUtils.showMessagePopup(getActivity(), "Distribution Created");
        popFragment();
	}
    @Override
    public boolean hasActionBarTitle() {
        return true;
    }

    @Override
    public String getActionBarTitle() {
        return getResources().getString(R.string.ab_create_distribution);
    }
}
