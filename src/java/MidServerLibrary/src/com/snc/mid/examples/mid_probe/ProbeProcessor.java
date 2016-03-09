package com.snc.mid.examples.mid_probe;

import java.lang.Thread.State;
import java.util.ArrayList;

import com.glide.util.ProcessRunner;
import com.glide.util.StringUtil;
import com.service_now.mid.probe.JavascriptProbe;
import com.service_now.mid.script.MIDSystem;
import com.service_now.mid.services.Config;
import com.snc.commons.MIDConfigParameter;

import org.apache.commons.codec.binary.Base64;

public class ProbeProcessor implements IProcessor
{
	  private JavascriptProbe _probe;
	  private MIDSystem _ms;
	  private DataThread _thread1;
	  
	  public ProbeProcessor(MIDSystem ms, JavascriptProbe probe)
	  {
	    set_probe(probe);
	    set_ms(ms);
	    
	    _thread1 = new DataThread(this);
	  }
	  
	  
	  public JavascriptProbe get_probe()
	  {
	    return this._probe;
	  }
	  
	  private void set_probe(JavascriptProbe _probe)
	  {
	    this._probe = _probe;
	    
	  }
	  
	  public MIDSystem get_ms()
	  {
	    return this._ms;
	  }
	  
	  private void set_ms(MIDSystem val)
	  {
	    this._ms = val;
	  }
	  
	  
	  
	  public void sendResult(String name, String output)
	  {
		  //Good practice to base64 encode the result.  This way you ensure you do not break the XML parser within the ServiceNow instance
	    String content = base64EncodeContent(output);
	    
	    get_probe().createOutputResult(content);
	    
	    String topic = get_probe().getParameter("topic");
	    String[] a = topic.split("\\.");
	    topic = a[(a.length - 1)];
	    get_probe().queue("mid.server." + Config.get().getProperty(MIDConfigParameter.MID_NAME), name, get_probe().getParameter("name"), get_probe().getParameter("source"), "");
	  }
	  
	  private String base64EncodeContent(String content)
	  {
	    byte[] encodedBytes = Base64.encodeBase64(content.getBytes());
	    String encodedStr = new String(encodedBytes);
	    return encodedStr;
	  }
	  
	  public Boolean process()
	  {
          String command = "ls -al";
          ProcessRunner p = new ProcessRunner();
          p.runCommand(command);
          ArrayList stdout = p.getOutputLines();
          ArrayList stderr = p.getErrorLines();
          String out = StringUtil.join(stdout, "\n");
          String err = StringUtil.join(stderr, "\n");
          sendResult("MyProbeName", out);
		  
	    return Boolean.valueOf(true);
	  }
	  
	  
	  public Boolean start() throws InterruptedException
	  {
		  Integer count = 0;
         _thread1.start();
         State st = _thread1.getState();
		while(st== State.RUNNABLE && count < 5) {
			Thread.sleep(10000);
			this.get_probe().createOutputResult("HEARTBEAT");
			this.get_probe().queue("Heartbeat", "MyProbeName", "MyProbeName");
			count++;
		}
         _thread1.stopServer();
         
	    return Boolean.valueOf(true);
	  }
	  
	  
	  public Boolean stop()
	  {
         
		  
	    return Boolean.valueOf(true);
	  }
	  
	  public Boolean sendComplete()
	  {
	    get_probe().createOutputResult("Success");
	    return Boolean.valueOf(true);
	  }
	}

