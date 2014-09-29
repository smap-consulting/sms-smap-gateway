package com.android.smap.fragments;

import java.util.ArrayList;
import java.util.List;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.adapters.ContactAdapter;
import com.android.smap.di.DataManager;
import com.android.smap.api.models.Contact;
import com.google.inject.Inject;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class ContactsFragment extends BaseFragment implements OnItemClickListener {

	@Inject
	private DataManager		mDataManager;
	private ContactAdapter	mAdapter;
	private List<Contact> list;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_contact,
				null);
		mDataManager = GatewayApp.getDependencyContainer().getDataManager();
		ListView listView = (ListView) view.findViewById(R.id.list_contacts);
		listView.setOnItemClickListener(this);
		list = mDataManager.getContacts();

		mAdapter = new ContactAdapter(getActivity(), R.layout.contact_allusers_rows, list);
		listView.setAdapter(mAdapter);
		
		return view;	
	}

	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {
		// TODO Auto-generated method stub
		Bundle B = new Bundle();
		
	}

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean hasActionBarTitle() {
        return true;
    }

    @Override
    public String getActionBarTitle() {
        return getResources().getString(R.string.ab_all_contacts);
    }
}
