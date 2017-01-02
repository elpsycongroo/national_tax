package jp.yuudachi.test.action;

import javax.annotation.Resource;

import jp.yuudachi.test.service.TestService;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	
	@Resource
	TestService testService;
	
	
	@Override
	public String execute() throws Exception {
		testService.say();
		return SUCCESS;
	}
}
