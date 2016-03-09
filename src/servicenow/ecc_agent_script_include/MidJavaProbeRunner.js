var MidJavaProbeRunner = Class.create();
MidJavaProbeRunner.prototype = Object.extendsObject(AProbe,{
	 _parms: {},
    initialize : function(probe) {
        this.probe = probe;
        this.output = "";
        this.createOutputRecord = true;
    },


    /**
     * Runs the probe instance
     */
    run : function() {
        try {
            this.init();
            this.process();
        } catch (ex) {
            ms.log(ex);
            this.setError(ex);
        } finally {
            this.cleanup();
        }
    },
      
    init : function() {
     
    },
  
    process : function() {
       try{
       	//Instantiate the processor
           var processor = new Packages.com.snc.mid.examples.mid_probe.ProbeProcessor(ms, this.probe);
           //Process or Start, depending on if this is a single processing or a server to run
           processor.start();
       }catch(e){
         ms.log(e);
         throw e;
       }
    },
    
    cleanup : function() {
    },     
   

	type: "MidJavaProbeRunner"
});