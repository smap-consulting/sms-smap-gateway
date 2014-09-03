package com.android.smap.fragments;

import java.util.ArrayList;
import java.util.List;

import com.android.smap.R;
import com.android.smap.adapters.ContactAdapter;
import com.android.smap.di.DataManager;
import com.android.smap.api.models.Contact;
import com.google.inject.Inject;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
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
	private List<Contact> list = new ArrayList<Contact>();

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout view = (LinearLayout) inflater.inflate(
				R.layout.fragment_contact,
				null);
		
		ListView listView = (ListView) view.findViewById(R.id.list_contacts);
		listView.setOnItemClickListener(this);
		
		Cursor phones = getActivity().getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		
		while (phones.moveToNext()) {
			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));	
			
			Contact contacts = new Contact();
			contacts.setName(name);
			contacts.setNumber(phoneNumber);
			list.add(contacts);
		}		
		phones.close();
		
		ContactAdapter objAdapter = new ContactAdapter(getActivity(), R.layout.contact_allusers_rows, list);
		listView.setAdapter(objAdapter);
		
		return view;	
	}
	
	// to implement functionality based on contact selected.
	@Override
	public void onItemClick(AdapterView<?> av, View parent, int pos, long viewId) {
		// TODO Auto-generated method stub
		Bundle B = new Bundle();
		
	}

}
