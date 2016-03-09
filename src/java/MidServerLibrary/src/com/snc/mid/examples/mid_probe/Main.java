package com.snc.mid.examples.mid_probe;

import org.json.JSONException;
import org.json.JSONStringer;

public class Main {

	public static void main(String[] args) throws JSONException {
		// TODO Auto-generated method stub
		 JSONStringer jj = new JSONStringer();
	      String s = jj.object()
	        .key("foo")
	        .value("bar")
	        .key("baz")
	        .array()
	        .object()
	        .key("quux")
	        .value("Thanks, Josh!")
	        .endObject()
	        .endArray()
	        .endObject()
	        .toString();
	      System.out.println(s);
	}

}
