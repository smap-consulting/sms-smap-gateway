package com.android.smap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.smap.R;
import com.android.smap.models.MenuDrawerItem;
import com.android.smap.ui.ArrayVelocAdapter;
import com.android.smap.ui.ViewQuery;

public class MenuDrawerAdapter extends ArrayVelocAdapter<MenuDrawerItem> {

	private static final MenuDrawerItem MENUITEM_ONE = new MenuDrawerItem(
			"Home");
	private static final MenuDrawerItem MENUITEM_TWO = new MenuDrawerItem(
			"Clients");
	private static final MenuDrawerItem MENUITEM_THREE = new MenuDrawerItem(
			"Endpoints");
	private static final MenuDrawerItem MENUITEM_FOUR = new MenuDrawerItem(
			"Settings");
	private static final MenuDrawerItem MENUITEM_FIVE = new MenuDrawerItem(
			"Contact");

	public MenuDrawerAdapter(Context context) {
		super(context);
		add(MENUITEM_ONE);
		add(MENUITEM_TWO);
		add(MENUITEM_THREE);
		add(MENUITEM_FOUR);
		add(MENUITEM_FIVE);
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {
		return inflator.inflate(R.layout.item_menudrawer, null, false);
	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {
		query.text(getItem(position).getName());
	}
}
