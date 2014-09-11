package com.android.smap.activities;

import java.util.List;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public abstract class BaseActivity extends FragmentActivity {

	private View	mLoadingLayout;

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
	}

	public void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	protected boolean isOrientationForced() {
		return true;
	}

	public void setLoading(boolean loading) {
		if (mLoadingLayout != null) {
			mLoadingLayout.setVisibility(loading ? View.VISIBLE : View.GONE);
		}
	}

	// for maintaining a fragment stack that all need to know about the result
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		List<Fragment> fragmentList = getSupportFragmentManager()
				.getFragments();
		for (Fragment fragment : fragmentList) {
			fragment.onActivityResult(requestCode, resultCode, data);
		}
	}

}