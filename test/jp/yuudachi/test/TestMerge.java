package jp.yuudachi.test;

import jp.yuudachi.test.entity.Person;
import jp.yuudachi.test.service.TestService;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMerge {
	
	private ClassPathXmlApplicationContext ctx;
	
	@Before
	public void loadCtx(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSpring(){
		TestService ts = (TestService) ctx.getBean("testService");
		ts.say();
	}
	@Test
	public void testHibernate(){
		SessionFactory sf = (SessionFactory) ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		//保存人员
		session.save(new Person("人员1"));
		transaction.commit();
		session.close();
		
	}
	@Test
	public void testServiceAndDao(){
		TestService ts = (TestService) ctx.getBean("testService");
//		ts.save(new Person("人员2"));
		System.out.println(ts.findPersonById("4028810c593ebeb001593ebeb1290000").getName());
	}
	
	@Test
	public void testTransationReadOnly(){//只读事务 如果在只读事务中出现更新即回滚
		TestService ts = (TestService) ctx.getBean("testService");
		System.out.println(ts.findPersonById("4028810c593ebeb001593ebeb1290000").getName());
	}
	
	@Test
	public void testRollback(){//操作中有任何异常即回滚先前的操作
		TestService ts = (TestService) ctx.getBean("testService");
		ts.save(new Person("人员3"));
	}
	
}
