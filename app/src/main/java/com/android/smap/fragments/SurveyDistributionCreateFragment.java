package com.android.smap.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.api.models.Distribution;
import com.android.smap.api.models.Survey;
import com.android.smap.ui.ViewQuery;
import com.android.smap.utils.MWUiUtils;

public class SurveyDistributionCreateFragment extends BaseFragment implements
		OnClickListener {

    public static final String		EXTRA_SURVEY_ID	= SurveyDistributionsFragment.class
                                                  .getCanonicalName()
                                                + "id";

	private EditText	name;
    private int			mSurveyId;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            mSurveyId = (int) savedInstanceState.getLong(EXTRA_SURVEY_ID);
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
        Distribution distribution = new Distribution();
        distribution.setName(name.getText().toString());
        distribution.setSurvey(Survey.findById((long) mSurveyId));
        distribution.save();

		MWUiUtils.hideKeyboard(getActivity());
		MWUiUtils.showMessagePopup(getActivity(), "Distribution Created");
	}

}
