package com.android.smap.api.requests;

import com.android.smap.GatewayApp;
import com.android.smap.api.models.Status;
import com.android.smap.utils.UriBuilder;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.gson.reflect.TypeToken;

public class DefinitionListRequest extends ApiRequest<Status> {

	public DefinitionListRequest(Listener<Status> listener,
			ErrorListener errorListener) {
		super(Method.GET, generateUrl(), listener, errorListener);
	}

	private static String generateUrl() {

		return new UriBuilder()
				.scheme(SCHEME_HTTP)
				.encodedAuthority(
						GatewayApp.getAppConfig().getRequestEndpoint())
				.appendEncodedPath(DEFINITION_LIST).build().toString();
	}
	
	@Override
	protected TypeToken<Status> getGsonTypeToken() {
		return new TypeToken<Status>() {};
	}

}
