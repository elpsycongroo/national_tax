package jp.yuudachi.test.dao;

import java.io.Serializable;

import jp.yuudachi.test.entity.Person;

public interface TestDao {
	// 保存人员
	public void save(Person person);

	// 查询
	public Person findPersonById(Serializable id);
}
