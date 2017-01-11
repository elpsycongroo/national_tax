package jp.yuudachi.core.service.impl;

import java.io.Serializable;
import java.util.List;

import jp.yuudachi.core.dao.BaseDao;
import jp.yuudachi.core.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		return baseDao.findObjects();
	}


}
