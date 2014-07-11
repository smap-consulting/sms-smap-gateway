package com.android.smap.fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.android.smap.R;
import com.android.smap.activities.BaseActivity;

/**
 * <pre>
 * BaseFragment acts as a superclass for all major fragments in the application.
 * 
 * BaseFragment provides:
 *      The new/next device-agnostic navigation 
 *      Resetting the action bar title as you navigate.
 * </pre>
 * 
 * BaseFragment also acts as a suitable bottleneck to attach behaviors such as
 * Google Analytics or Deep Linking.
 * 
 * @author matt witherow
 */

public class BaseFragment extends Fragment {

	public static final String	EXTRA_DEEP_LINK_DATA	= BaseFragment.class
																.getName()
																+ ".EXTRA_DEEP_LINK_DATA";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (hasActionBarTitle()) {
			getActionBar().setTitle(getActionBarTitle());
		}

	}

	@Override
	public void onDetach() {
		super.onDetach();
		if (hasActionBarTitle()) {
			getActionBar().setTitle(getString(R.string.app_name));
		}
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		BaseActivity b = ((BaseActivity) getActivity());
		b.setupActionBar();
	}

	/**
	 * Function to get the action bar instance
	 * 
	 * @return
	 */
	public ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	/**
	 * full screen regardless of tablet or phone
	 * 
	 * @return
	 */
	public boolean isFullScreen() {
		return false;
	}

	/**
	 * Whether or not this fragment wants to display a custom action bar title.
	 * Subclasses that return true can return the action bar title that they
	 * wish to display from getActionBarTitle. When a fragment that returns true
	 * in hasActionBarTitle is detached the action bar title will be reset to
	 * the app name.
	 * 
	 * @return
	 */
	public boolean hasActionBarTitle() {
		return false;
	}

	/**
	 * The action bar title to use when this fragment is displayed. Note: if
	 * this fragment wants this title to be displayed it must also override
	 * hasActionBarTitle and return true
	 * 
	 * @return
	 */
	public String getActionBarTitle() {
		return "";
	}

	/**
	 * Push a new fragment onto the stack.
	 * 
	 * @param cls
	 *            The fragment class to push.
	 */
	public void pushFragment(Class<?> cls) {
		pushFragment(cls, null);
	}

	/**
	 * Push a new fragment onto the stack.
	 * 
	 * @param cls
	 *            The fragment class to push.
	 * @param args
	 *            The arguments provided to the fragment.
	 */
	public void pushFragment(Class<?> cls, Bundle args) {
		Fragment f = Fragment.instantiate(getActivity(), cls.getName());
		f.setArguments(args);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, f);
		ft.addToBackStack(null);
		ft.commit();
	}

	/**
	 * Replaces the current fragment. This can be used for adding the initial
	 * fragment as well as replacing.
	 * 
	 * @param cls
	 *            The fragment that will replace the current fragment.
	 */
	public void replaceFragment(Class<?> cls) {
		replaceFragment(cls, null);
	}

	/**
	 * Replaces the current fragment. This can be used for adding the initial
	 * fragment as well as replacing.
	 * 
	 * @param cls
	 *            The fragment that will replace the current fragment.
	 * @param args
	 *            The arguments provided to the fragment.
	 */
	public void replaceFragment(Class<?> cls, Bundle args) {
		Fragment f = Fragment.instantiate(getActivity(), cls.getName());
		f.setArguments(args);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, f);
		ft.commit();
	}
}
