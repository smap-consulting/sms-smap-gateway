package com.android.smap.di.modules;

import com.android.smap.di.DataManager;
import com.android.smap.di.mocks.MockDataManager;
import com.google.inject.AbstractModule;

/**
 * All dependency injection bindings for the app data layer.
 * 
 * @author Matt Witherow
 * 
 */
public class DataLayerModule extends AbstractModule {

	@Override
	protected void configure() {
		/*
		 * This tells Guice that whenever it sees a dependency on a X, it should
		 * satisfy the dependency using Y.
		 * 
		 * bind(X).to(Y);
		 */

		bind(DataManager.class).to(MockDataManager.class);

	}

}
