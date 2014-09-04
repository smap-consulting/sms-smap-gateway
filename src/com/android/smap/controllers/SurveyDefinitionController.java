package com.android.smap.controllers;

import org.smap.surveyModel.SurveyModel;

import android.content.Context;

import com.android.smap.api.models.Survey;
import com.android.smap.api.requests.SmapRawRequest;
import com.android.smap.api.requests.SurveyDefinitionRequest;

public class SurveyDefinitionController extends
		RawRequestController<Survey> {

	private String	mFormKey;

	public SurveyDefinitionController(Context context,
			ControllerListener listener,
			ControllerErrorListener errorListener,
			String formKey) {
		super(context, listener, errorListener);
		mListener = listener;
		mErrorListener = errorListener;
		mFormKey = formKey;
	}

	@Override
	protected SmapRawRequest getRequest() {
		return new SurveyDefinitionRequest(this, this, mFormKey);
	}

	@Override
	protected Survey addResponseToDatabase(String rawXML) {
		// TODO Auto-generated method stub

		Survey survey= new Survey();
		
		SurveyModel sm = new SurveyModel(rawXML);
		
		
		
		String name = ;
		
		
		survey.setName(name);
		
		
		// save to db

		// return the model, rturn true :

		return null;
	}

}
