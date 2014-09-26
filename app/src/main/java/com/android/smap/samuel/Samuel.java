package com.android.smap.samuel;

import com.android.smap.models.SmapTextMessage;
import com.android.smap.models.TextMessage;

/**
 * Parser for inbound SMS's.
 * 
 * @author Matt Witherow
 * 
 */
public class Samuel {

	private final static String	NEW_LINE					= System.getProperty("line.separator");
	private static final String	SMAP_IDENTIFIER				= "#!";

	public static boolean isSmapRelatedSMS(String message) {
		return message.startsWith(SMAP_IDENTIFIER);
	}

    public static SmapTextMessage parse(String number, String message){

        if(Samuel.isSmapRelatedSMS(message)){
            SmapTextMessage smapMessage = new SmapTextMessage(number, message);
            smapMessage.setValid(true);
            smapMessage.direction = TextMessage.INCOMING;
            smapMessage.status = TextMessage.RECEIVED;
            return smapMessage;
        }
        return null;

    }


}
