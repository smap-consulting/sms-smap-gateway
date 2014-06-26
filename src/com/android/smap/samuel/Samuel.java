package com.android.smap.samuel;

import java.util.ArrayList;

import com.android.smap.api.models.Gojo;
import com.android.smap.api.models.Gojo.Command;
import com.android.smap.api.models.Node;

/**
 * Parser for inbound SMS's.
 * 
 * @author Matt Witherow
 * 
 */
public class Samuel {

	private final static String	NEW_LINE		= System.getProperty("line.separator");
	private static final String	COMMAND_HASH	= "#";
	public static final String	EMAIL			= "e";
	public static final String	SUBJECT			= "s";
	public static final String	MSG				= "m";

	public static Gojo parse(String message) {
		// TODO
		// this is just a demo hack

		Gojo gojo = new Gojo();
		ArrayList<Node> list = new ArrayList<Node>();

		// ArrayList<String> lines = new ArrayList<String>();
		for (String line : message.split(NEW_LINE)) {

			if (checkForCommand(line, gojo)) {
				continue;
			}
			String[] pairs = line.split(":");
			Node n = new Node();
			n.key = pairs[0];
			n.val = pairs[1];
			list.add(n);
		}
		gojo.body = list;
		// gojo.body.add(addNode(line));
		return gojo;
	}

	// this is literally a joke method for the prototype
	// private static Node addNode(String line) {
	//
	// Node node = new Node();
	//
	// if (hasChildren(line)) {
	// node.root = line.split("(")[0];
	// String items = line.split("\\[(.*?)\\]")[0];
	// node.children = addChildNode(items);
	// }
	//
	// // standard line
	// if (line.contains(":")) {
	// node.key = line.split(":")[0];
	// node.val = line.split(":")[1];
	// }
	//
	// return node;
	//
	// }
	//
	// private static List<Node> addChildNode(String items) {
	//
	// ArrayList<Node> arrlist = new ArrayList<Node>();
	// // list items
	// if (items.contains(",")) {
	// String[] arr = items.split(",");
	// for (String str : arr) {
	// Node n = new Node();
	// n.root = str;
	// arrlist.add(n);
	// }
	// }
	// return arrlist;
	// }

	private static boolean hasChildren(String line) {

		if (line.contains("(") || line.contains(")")) {
			return true;
		}
		return false;

	}

	private static boolean checkForCommand(String line, Gojo gj) {
		if (line.startsWith(COMMAND_HASH)) {
			// take the hash off
			Command cmd = Command.getCommand(line.substring(1));
			if (cmd != null) {
				gj.cmd = cmd;
				return true;
			}
		}
		return false;

	}
}
