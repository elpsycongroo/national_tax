package jp.yuudachi.core.dao;

import java.io.Serializable;
import java.util.List;

import jp.yuudachi.core.util.QueryHelper;


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
	//条件查询实体列表
	List<T> findObjects(String hql, List<Object> parameters);
	//条件查询实体列表--QueryHelper
	public List<T> findObjects(QueryHelper queryHelper);
}
