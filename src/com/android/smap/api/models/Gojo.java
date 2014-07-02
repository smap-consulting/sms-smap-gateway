package com.android.smap.api.models;

import com.google.gson.annotations.Expose;

/**
 * Goal Oriented Java Object
 * 
 * @author Matt Witherow
 * 
 */
public class Gojo {

	@Expose
	public Goal		goal;
	@Expose
	public String	model;
	public String	reference;

	public enum Goal {

		SUBSCRIBE("sub"),
		UNSUBSCRIBE("unsub"),
		REDO("redo"),
		RESET("reset"),
		ANSWER("");

		String	mStr;

		Goal(String str) {
			this.mStr = str;
		}

		public String toString() {
			return mStr;
		}

		public static Goal init(String command) {

			for (Goal goal : Goal.values()) {
				if (command.compareToIgnoreCase(goal.toString()) == 0) {
					return goal;
				}
			}
			return null;
		}
	}
}
