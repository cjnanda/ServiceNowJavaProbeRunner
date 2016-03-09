var JavaProbe = Class.create();
JavaProbe.prototype = {
    initialize: function() {
    },

    send: function(){
    	//Pass in Mid Server name, usually would come from a property or a setting table
    	var probe = new JavascriptProbe("Vendor_Mid_01");
    	//Name is the name of the Mid Server Script Include
		probe.setName("MidJavaProbeRunner");
		//Source is the location the script would run, not really used for Javascript probes, but set nonetheless.
		probe.setSource("127.0.0.1");
		//Create inserts into the ECC queue and returns the sys_id of the ECC Queue record
		probe.create();
    },

    type: 'JavaProbe'
};