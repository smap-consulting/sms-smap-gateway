package com.android.smap;

import com.android.smap.di.modules.DataLayerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class DependencyContainer {

	Injector	mInjector;

	public DependencyContainer() {

		mInjector = Guice.createInjector(new DataLayerModule());

	}

	public Injector getInjector() {
		return mInjector;
	}

}
