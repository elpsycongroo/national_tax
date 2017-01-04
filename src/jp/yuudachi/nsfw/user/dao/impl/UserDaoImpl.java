package jp.yuudachi.nsfw.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import jp.yuudachi.core.dao.BaseDaoImpl;
import jp.yuudachi.nsfw.user.dao.UserDao;
import jp.yuudachi.nsfw.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		String hql = "FROM User WHERE account=?";
		if(StringUtils.isNotBlank(id)){
			hql += " AND id!='" + id +"'";
		}
		Query query = getSession().createQuery(hql);
		query.setParameter(0, account);
		return query.list();
	}


}
