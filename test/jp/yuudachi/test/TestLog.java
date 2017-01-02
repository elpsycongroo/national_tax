package jp.yuudachi.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;



public class TestLog {
	@Test
	public void test(){
		Log log = LogFactory.getLog(getClass());
		try{
			int i = 1/0;
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		log.debug("调试级别日志");
		log.info("info级别日志");
		log.warn("warn级别日志");
		log.error("error级别日志");
		log.fatal("fatal级别日志");
	}
}
	
