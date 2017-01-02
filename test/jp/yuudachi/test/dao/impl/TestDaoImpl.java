package jp.yuudachi.test.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import jp.yuudachi.test.dao.TestDao;
import jp.yuudachi.test.entity.Person;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao {

	
	@Override
	public void save(Person person) {
		getSessionFactory();
		getHibernateTemplate().save(person);
		int i = 1/0;
	}

	@Override
	public Person findPersonById(Serializable id) {
//		save(new Person("test"));
		return getHibernateTemplate().get(Person.class, id);
	}

}
