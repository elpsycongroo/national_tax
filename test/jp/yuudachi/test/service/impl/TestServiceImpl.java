package jp.yuudachi.test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.yuudachi.test.dao.TestDao;
import jp.yuudachi.test.entity.Person;
import jp.yuudachi.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Resource
	TestDao testDao;
	
	@Override
	public void say() {
		System.out.println("HELLO SPRING");
		
	}

	@Override
	public void save(Person person) {
		testDao.save(person);
		
	}

	@Override
	public Person findPersonById(Serializable id) {
		return testDao.findPersonById(id);
	}
}
