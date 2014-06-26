package com.android.smap.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.smap.R;
import com.android.smap.adapters.MenuDrawerAdapter;
import com.android.smap.fragments.BaseFragment;
import com.android.smap.fragments.HomeFragment;
import com.android.smap.sms.GatewayService;
import com.android.smap.ui.ViewQuery;

public class MainActivity extends BaseActivity implements OnItemClickListener {

	private ViewQuery	mQuery;
	private ViewQuery	mMenuQuery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupActionBar();
		setInitialFragment(HomeFragment.class);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		mQuery = new ViewQuery(this);
		mMenuQuery = mQuery.find(R.id.drawer_left);
		mMenuQuery.adapter(new MenuDrawerAdapter(this)).onItemClicked(this);
		mMenuQuery.background(R.color.loading_bg);

	}

	private void setInitialFragment(Class<? extends BaseFragment> fclass) {
		Fragment f = Fragment.instantiate(this, fclass.getName());
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.container, f);
		ft.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		startService(new Intent(this, GatewayService.class));
	}

	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {

			DrawerLayout drawer = mQuery.find(R.id.layout_drawer).get(
					DrawerLayout.class);
			View menuView = mMenuQuery.get();
			if (drawer.isDrawerOpen(menuView)) {
				drawer.closeDrawer(menuView);
			} else {
				drawer.openDrawer(menuView);
			}

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// push new screen
		// TODO
	}

}
