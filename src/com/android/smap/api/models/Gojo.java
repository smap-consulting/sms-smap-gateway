package com.android.smap.api.models;

import android.util.Log;

import com.android.smap.controllers.Controller;
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

	public void go() {
		try {
			Controller c = goal.mHandler.newInstance();
			c.start();
		}
		catch (InstantiationException | IllegalAccessException e) {

			Log.e(Gojo.class.getName(), "NO CONTROLLER FOUND");
		}
	}
}
