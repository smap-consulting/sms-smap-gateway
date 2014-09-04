package com.android.smap.controllers;

import android.content.Context;

import com.android.smap.api.models.SurveyDefinition;
import com.android.smap.api.requests.SmapRawRequest;
import com.android.smap.api.requests.SurveyDefinitionRequest;

public class SurveyDefinitionsController extends
		RawRequestController<SurveyDefinition> {

	public SurveyDefinitionsController(Context context,
			ControllerListener listener,
			ControllerErrorListener errorListener) {
		super(context, listener, errorListener);
		mListener = listener;
		mErrorListener = errorListener;
	}

	@Override
	protected SmapRawRequest getRequest() {
		return new SurveyDefinitionRequest(this, this);
	}

	@Override
	protected SurveyDefinition addResponseToDatabase(String rawXML) {
		// TODO Auto-generated method stub
		return null;
	}

}
