package com.android.smap.api.requests;

import com.android.smap.GatewayApp;
import com.android.smap.utils.UriBuilder;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

public class FormListRequest extends SmapRawRequest {

	public FormListRequest(Listener<String> listener,
			ErrorListener errorListener) {
		super(Method.GET, generateUrl(), listener, errorListener);
	}

	private static String generateUrl() {

		String protocol = SCHEME_HTTPS;
		String server = GatewayApp.getAppConfig().getRequestEndpoint();
		if(server.equals("10.0.2.2")) {
			protocol = SCHEME_HTTP;

		}
		return new UriBuilder()
				.scheme(protocol)
				.encodedAuthority(server)
				.appendEncodedPath(FORM_LIST).build().toString();
	}
}
