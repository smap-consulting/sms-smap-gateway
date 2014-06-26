package com.android.smap;

import android.app.Application;

import com.android.smap.utils.BitmapLruCache;
import com.android.smap.utils.PreferenceWrapper;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * The main {@link Application} class. Singleton that provides access to main
 * utilities and global components.
 * 
 * @author matt witherow
 */
public class GatewayApp extends Application {

	private static GatewayApp			sInstance;
	private static AppConfig			sAppConfig;
	private static RequestQueue			sRequestQueue;
	private static ImageLoader			sImageLoader;
	private static PreferenceWrapper	sPreferenceWrapper;
	private PhoneStateWrapper			sPhoneStateWrapper;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = GatewayApp.this;
		sAppConfig = new AppConfig(this);
		sRequestQueue = Volley.newRequestQueue(this, null);
		sImageLoader = new ImageLoader(sRequestQueue,
				new BitmapLruCache());
		sPreferenceWrapper = new PreferenceWrapper(this);
		sPhoneStateWrapper = new PhoneStateWrapper(this);
	}

	/**
	 * Returns the {@link PreferenceWrapper}
	 * 
	 * @return
	 */
	public static PreferenceWrapper getPreferenceWrapper() {
		return sPreferenceWrapper;
	}

	/**
	 * Returns an instance of the SMAP Application 
	 * @return
	 */
	public static GatewayApp getInstance() {
		return sInstance;
	}

	/**
	 * Returns the Application Configuration (managed by the companion app)
	 * 
	 * @return
	 */
	public static AppConfig getAppConfig() {
		return sAppConfig;
	}

	/**
	 * Returns an ImageLoader
	 * 
	 * @return
	 */
	public static ImageLoader getImageLoader() {
		return sImageLoader;
	}

	/**
	 * Returns a phone state convenience wrapper.
	 * 
	 * @return
	 */
	public PhoneStateWrapper getPhoneStateWrapper() {
		return sPhoneStateWrapper;
	}

	public static RequestQueue getRequestQueue() {
		return sRequestQueue;
	}

}
