package com.android.smap;

import com.android.smap.api.ApiConstants;

public class AppConfig {

    private int testCounter = 0;

    public void incrementCounter(){
        testCounter++;
    }

    public int getCount(){
        return testCounter;
    }

	public AppConfig(GatewayApp gatewayApp) {
		// convenience wrapper for android's application settings.
	}

	public int getTimeoutInMillis() {
		// TODO timeout for Volley requests
		return 0;
	}

	public String getRequestEndpoint() {
		return ApiConstants.DEV_URL;
	}

}
