package com.android.smap;

import com.android.smap.api.ApiConstants;

public class AppConfig {

	public AppConfig(GatewayApp gatewayApp) {
		// convenience wrapper for android's application settings.
	}

	public int getTimeoutInMillis() {
		// TODO timeout for Volley requests
		return 0;
	}

	public String getRequestEndpoint() {
        String server = GatewayApp.getPreferenceWrapper().getServer();
        if(server.startsWith("https://")) {
            server = server.substring(8);
        } else  if(server.startsWith("http://")) {
            server = server.substring(7);
        }
		return server;
		//return ApiConstants.PRODUCTION_URL;
	}

}
