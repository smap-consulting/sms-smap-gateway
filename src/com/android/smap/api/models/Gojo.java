package com.android.smap.api.models;

import java.util.List;

import com.android.smap.controllers.Controller;
import com.google.gson.annotations.Expose;

/**
 * Goal Oriented Java Object
 * 
 * @author Matt Witherow
 * 
 */
public class Gojo {

	public Controller	controller;
	@Expose
	public Command		cmd;
	@Expose
	public List<Node>	body;

	public enum Command {

		EMAIL("email"),
		POST("post");

		String	mStr;

		Command(String str) {
			this.mStr = str;
		}

		public String toString() {
			return mStr;
		};

		public static Command getCommand(String str) {

			for (Command cmd : Command.values()) {
				if (str.compareToIgnoreCase(cmd.toString()) == 0) {
					return cmd;
				}
			}
			return null;
		}

	}

	/**
	 * Returns value for a key. TODO make recursive for all children
	 * 
	 * @param key
	 * @return
	 */
	public String find(String key) {

		for (Node node : body) {
			if (node.key == null) {
				continue;
			}
			if (node.key.compareToIgnoreCase(key) == 0) {
				return node.val;
			}
		}
		return null;
	}

}
