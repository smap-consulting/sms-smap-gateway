package com.android.smap.di;

import java.util.List;

import com.android.smap.api.models.Survey;
import com.android.smap.api.models.SurveyDetail;

public interface DataManager {

	List<Survey> getSurveys();

	SurveyDetail getDetailsForSurvey(int mSurveyId);

}
