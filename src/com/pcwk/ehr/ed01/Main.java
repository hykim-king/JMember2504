package com.pcwk.ehr.ed01;

//import 정리 단축키 : ctrl+shift+o
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	static final Logger LOG = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		//FATAL>ERROR>WARN>INFO>DEBUG>TRACE
		LOG.debug("debug Hello, world!");
		LOG.info("info");
		LOG.warn("warn");
		LOG.error("error");
		LOG.fatal("fatal");
	}

}
