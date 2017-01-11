package jp.yuudachi.core.service;

import java.io.Serializable;
import java.util.List;

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

}
