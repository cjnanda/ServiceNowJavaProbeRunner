var JavaProbeSensor = Class.create();
JavaProbeSensor.prototype = {
	_eccGr: null,
	_payloadXml: null,
    initialize: function(eccGr) {
    	this._eccGr = eccGr;
    },
	
	process: function(){
		var probe = Probe.createProbeResponse(this._eccGr);
		this._payloadXml = probe.getPayload();
		var output = this._getOutput();
		gs.info(gs.base64Decode(output));
	},

	 _getOutput: function(){
            if(this._payloadXml != ""){
                var xmlDoc = new XMLDocument2();
                xmlDoc.parseXML(this._payloadXml);
                var output = xmlDoc.getNodeText("//results/result/output");
                gs.info("OUtput: " + output);
                return output;
            }
        return null;
    },

    type: 'JavaProbeSensor'
};

JavaProbeSensor.isProbe = function(current){
	if(current.topic.toString() == "MyProbeResult" && current.name.toString() == "MidJavaProbeRunner" && current.queue == "input"){
		return true;
	}
	return false;
};