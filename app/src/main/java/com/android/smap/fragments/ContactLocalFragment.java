package com.android.smap.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.smap.GatewayApp;
import com.android.smap.R;
import com.android.smap.adapters.ContactLocalAdapter;
import com.android.smap.api.models.Contact;
import com.android.smap.di.DataManager;
import com.android.smap.ui.ViewQuery;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import com.mjw.android.swipe.MultiChoiceSwipeListener;
import com.mjw.android.swipe.SwipeListView;

/**
 * Created by marlonatton on 2/10/2014.
 */
public class ContactLocalFragment extends BaseFragment {


    @Inject
    private DataManager mDataManager;
    private SwipeListView			mSwipeListView;
    private List<Contact>	mModel = new ArrayList<Contact>();
    private ContactLocalAdapter mAdapter;

    @Override
    public View onCreateContentView(LayoutInflater inflater, Bundle savedInstanceState) {
        LinearLayout view = (LinearLayout) inflater.inflate(
                R.layout.fragment_local_contact,
                null);

        mDataManager = GatewayApp.getDependencyContainer().getDataManager();

        ViewQuery query = new ViewQuery(view);
        mSwipeListView = (SwipeListView) query.find(R.id.list_local_contacts).get();

        Cursor phones = getActivity().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        while (phones.moveToNext()) {
            Contact localContact = new Contact();

            localContact.setName(phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));

            localContact.setNumber(phones
                    .getString(phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));

            mModel.add(localContact);
        }
        phones.close();
        localContactSetup();

        return view;
    }

    public void localContactSetup(){
        mAdapter = new ContactLocalAdapter(getActivity(), mModel, mSwipeListView);
        mSwipeListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);


        ////////////////

        mSwipeListView
                .setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                    @Override
                    public void onItemCheckedStateChanged(ActionMode mode,
                                                          int position,
                                                          long id, boolean checked) {
                        mode.setTitle(String.format("Add (%d) Contacts",
                                mSwipeListView.getCountSelected()));
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode,
                                                       MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_add:
                              //  addContactsToSurvey();
                                addLocalContacts();
                                mode.finish();
                                getActivity().onBackPressed();
                                return true;
                            default:
                                return false;
                        }

                    }

                    @Override
                    public boolean onCreateActionMode(ActionMode mode,
                                                      Menu menu) {
                        MenuInflater inflater = mode.getMenuInflater();
                        inflater.inflate(R.menu.menu_add, menu);
                        return true;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        mSwipeListView.unselectedChoiceStates();
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode,
                                                       Menu menu) {
                        return false;
                    }
                });
//
        mSwipeListView.setSwipeListViewListener(new MultiChoiceSwipeListener(
                mAdapter));
        mSwipeListView.setAdapter(mAdapter);

  //  }
    }

    public void addLocalContacts(){
        List<Integer> selected = mSwipeListView.getPositionsSelected();
        List<Contact> contacts = new ArrayList<Contact>();
    //need to implement save method
        for (Integer i : selected) {
            contacts.add(mModel.get(i));
        }
        mDataManager.addPhoneContactToSmapContact(contacts);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = false;
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return handled;

    }

}
