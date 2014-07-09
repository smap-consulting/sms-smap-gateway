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

	private static final MenuDrawerItem	MENUITEM_SURVEYS	= new MenuDrawerItem(
																	"Surveys",
																	android.R.drawable.ic_media_play);
	private static final MenuDrawerItem	MENUITEM_CONTACTS	= new MenuDrawerItem(
																	"Contacts",
																	android.R.drawable.ic_media_play);
	private static final MenuDrawerItem	MENUITEM_SERVERS	= new MenuDrawerItem(
																	"Servers",
																	android.R.drawable.ic_media_play);
	private static final MenuDrawerItem	MENUITEM_SETTINGS	= new MenuDrawerItem(
																	"Settings",
																	android.R.drawable.ic_media_play);
	private static final MenuDrawerItem	MENUITEM_INFO		= new MenuDrawerItem(
																	"Info",
																	android.R.drawable.ic_media_play);

	public MenuDrawerAdapter(Context context) {
		super(context);
		add(MENUITEM_SURVEYS);
		add(MENUITEM_CONTACTS);
		add(MENUITEM_SERVERS);
		add(MENUITEM_SETTINGS);
		add(MENUITEM_INFO);
	}

	@Override
	public View newView(LayoutInflater inflator, int position, ViewGroup parent) {
		return inflator.inflate(R.layout.item_menudrawer, null, false);
	}

	@Override
	public void bindView(Context context, View view, ViewQuery query,
			int position) {
		query.find(R.id.txt_title).text(getItem(position).getName());
		query.find(R.id.ic_menu_item).image(getItem(position).getId());
	}
}
