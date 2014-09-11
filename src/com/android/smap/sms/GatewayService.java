package com.android.smap.sms;

import java.io.IOException;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.smap.commonsware.wakefull.WakefulIntentService;
import com.android.smap.controllers.ControllerError;
import com.android.smap.controllers.ControllerErrorListener;
import com.android.smap.controllers.ControllerListener;
import com.android.smap.models.TextMessage;
import com.android.smap.samuel.Samuel;

public class GatewayService extends Service implements
		CellularModem.SmsModemListener,
		ControllerListener,
		ControllerErrorListener {

	public static final String		TAG		= GatewayService.class
													.getCanonicalName();

	public static GatewayService	s_this	= null;

	public CellularModem			modem;

	public static GatewayService get() {
		return s_this;
	}

	public static boolean	doReset	= false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		s_this = this;
		modem = new CellularModem(getApplicationContext(), this);
		Log.d(TAG, "RelayService Created.");
		promoteErroredMessages();
		kickService();
	}

	/***
	 * This should be run only when our service starts, and takes care of
	 * resending any messages that were queued but which we never got a reply
	 * for. This could result in double sends but that's better than leaving a
	 * message on the floor.
	 */
	public void promoteErroredMessages() {

		// for all messages that never got replied to (and so never had their
		// pending status revoked. ) ?
		// call sendPendingMessagesToServer?
		// call only at startup
		// TODO

	}

	/***
	 * Goes through all the messages which have errors and resends them. Note
	 * that we only try to send five at a time, so it could take a bit to clear
	 * out the backlog.
	 */
	public void resendErroredSMS() {

		// TODO
	}

	/**
	 * Sends all our pending outgoing messages to our server.
	 */
	public void sendPendingMessagesToServer() throws IOException {
		List<TextMessage> msgs = null;

		// first send any that haven't yet been tried
		// get all the msgs with fresh status
		for (TextMessage msg : msgs) {
			sendMessageToServer(msg);
		}

		// then those that had an error when we tried to contact the server
		// get all the error sattus msgs
		for (TextMessage msg : msgs) {
			sendMessageToServer(msg);
		}
	}

	public void markDeliveries() {

		// change status to delivered
	}

	/**
	 * Sends a message to our server.
	 * 
	 * @param msg
	 */
	public void sendMessageToServer(TextMessage msg) throws IOException {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String receiveURL = prefs.getString("receive_url", null);
		boolean process_outgoing = prefs.getBoolean("process_outgoing", false);

		// message

		// send volley request
	}

	public void markMessageDelivered(TextMessage msg) {

		// update status of message, pending becomes sent. etc
	}

	// triggers our background service to go do things
	public void kickService() {
		WakefulIntentService.sendWakefulWork(this, MonitorService.class);
	}

	public void sendMessage(TextMessage msg) {

		Log.d(TAG, "=== SMS OUT: " + msg.number + ": " + msg.text);
		try {
			modem.sendSms(msg.number, msg.text, "" + msg.id);
		}
		catch (Exception e) {
			msg.status = TextMessage.ERRORED;
		}

	}

	// SMS recieved by gateway
	public void onNewSMS(String number, String message) {

		// commands could be called, return feedback straight away
		// if(message == reset){
		// modem.sendSMS(num, "resetting");
		// kickService(reset);

		// sendSMS or KickService basically...

		// TODO: MW 
		if (Samuel.isSmapRelatedSMS(message)) {
			//?? = Samuel.parse(message);
			
		}

		// kickService();
	}

	public void onSMSSendError(String token, String errorDetails) {
		TextMessage msg = null;

		// change status, add to backlog
	}

	public void onSMSSent(String token) {

		// change status to sent,
		// kickService()
	}

	@Override
	public void onControllerError(ControllerError error) {
		// TODO error with request handeling.

	}

	@Override
	public void onControllerResult() {
		// TODO succesfull outbound request

	}

}