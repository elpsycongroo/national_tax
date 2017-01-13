package jp.yuudachi.core.service;

import java.io.Serializable;
import java.util.List;

import jp.yuudachi.core.util.QueryHelper;
import jp.yuudachi.nsfw.info.entity.Info;

public interface BaseService<T> {

	// save
	public void save(T t);

	// update
	public void update(T t);

	// delete by id
	public void delete(Serializable id);

	// find by id
	public T findObjectById(Serializable id);

	// find list
	public List<T> findObjects();
	
	@Deprecated
	//条件查询实体列表
	List<T> findObjects(String hql, List<Object> parameters);

	//根据条件查询试题列表--queryHelper
	List<T> findObjects(QueryHelper queryHelper);
}
