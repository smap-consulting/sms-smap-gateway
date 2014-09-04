package com.android.smap.api.requests;

import com.android.smap.GatewayApp;
import com.android.smap.utils.UriBuilder;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class SurveyDefinitionRequest extends SmapRawRequest {

	public SurveyDefinitionRequest(Listener<String> listener,
			ErrorListener errorListener, String xmlFormKey) {
		super(Method.GET, generateUrl(xmlFormKey), listener, errorListener);
	}

	private static String generateUrl(String xmlKey) {
		return new UriBuilder()
				.scheme(SCHEME_HTTP)
				.encodedAuthority(
						GatewayApp.getAppConfig().getRequestEndpoint())
				.appendEncodedPath(FORM_XML)
				.appendQueryParameter(FORM_XML_KEY, xmlKey).build().toString();
	}
}
