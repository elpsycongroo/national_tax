package jp.yuudachi.test.service;

import java.io.Serializable;

import jp.yuudachi.test.entity.Person;

public interface TestService {
	
	//输出
	public void say();
	//保存人员
	public void save(Person person);
	//查询
	public Person findPersonById(Serializable id);
	
}
