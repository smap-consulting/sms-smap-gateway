package com.android.smap.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.adapters.ContactAdapter;
import com.android.smap.api.models.Contact;
import com.android.smap.api.models.Survey;
import com.android.smap.controllers.ContactImportController;
import com.android.smap.di.DataManager;
import com.google.inject.Inject;

import org.smap.surveyModel.SurveyModel;

import java.util.List;


public class ContactsFragment extends BaseFragment implements OnItemClickListener {

    @Inject
    private DataManager mDataManager;
    private ContactAdapter mAdapter;
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

//		Cursor phones = getActivity().getContentResolver().query(
//				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
//				null, null);
//		
//		while (phones.moveToNext()) {
//			String name = phones
//					.getString(phones
//							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//
//			String phoneNumber = phones
//					.getString(phones
//							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));	
//			
//			Contact contacts = new Contact();
//			contacts.setName(name);
//			contacts.setNumber(phoneNumber);
//			list.add(contacts);
//		}		
//		phones.close();

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_settings) {

            Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(contactPickerIntent, ContactImportController.PICK_CONTACTS);

        } else {
            getActivity().onBackPressed();
        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


            if (resultCode == Activity.RESULT_OK) {
                ContactImportController contactsManager = new ContactImportController(getActivity(), data);
                String name = "";
                String number = "";
                try {
                    name = contactsManager.getContactName();
                    number = contactsManager.getContactPhone();

                    Contact contact = new Contact(name, number);
                    contact.setActive(true);
                    contact.save();
                } catch (Exception e) {
                    Log.e("CONTACTS", e.getMessage());
                }

            }


    }
}

