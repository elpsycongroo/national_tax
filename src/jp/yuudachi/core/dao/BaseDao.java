package jp.yuudachi.core.dao;

import java.io.Serializable;
import java.util.List;


public interface BaseDao<T> {
	//save
	public void save(T entity);
	//update
	public void update(T entity);
	//delete by id
	public void delete(Serializable id);
	//find by id
	public T findObjectById(Serializable id);
	//find list
	public List<T> findObjects();
}
