package com.android.smap.api.models;

import com.google.gson.annotations.Expose;


/**
 * Body node for a GOJO.
 * 
 * @author Matt Witherow
 * 
 */
public class Node {

	// public String root; //?
	@Expose
	public String	key;
	@Expose
	public String	val;
	// public List<Node> children; //TODO

}
