package com.snc.mid.examples.mid_probe;

import org.json.JSONException;
import org.json.JSONStringer;

import com.service_now.mid.probe.JavascriptProbe;

public class DataThread extends Thread {
	private ProbeProcessor _processor;
	
	private Boolean _isRunning;
	private IProcessor _parent;
	
	public DataThread(IProcessor parent){
		set_parent(parent);
		set_isRunning(false);
	}
	
	 public void run() {
        startServer();
    }
	
	
	private void startServer(){
		set_isRunning(true);
		while(get_isRunning()){
			try {
				Thread.sleep(2000);
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
				get_parent().sendResult("MyProbeResult", s);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void stopServer(){
		set_isRunning(false);
	}

	private IProcessor get_parent() {
		return _parent;
	}

	private void set_parent(IProcessor _parent) {
		this._parent = _parent;
	}
	
	private JavascriptProbe get_probe()
	  {
	    return get_parent().get_probe();
	  }

	public Boolean get_isRunning() {
		return _isRunning;
	}

	public void set_isRunning(Boolean _isRunning) {
		this._isRunning = _isRunning;
	}
	
}
