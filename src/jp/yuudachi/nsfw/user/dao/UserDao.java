package jp.yuudachi.nsfw.user.dao;

import java.util.List;

import jp.yuudachi.core.dao.BaseDao;
import jp.yuudachi.nsfw.user.entity.User;

public interface UserDao extends BaseDao<User> {
	//根据用户名和ID查询用户
	List<User> findUserByAccountAndId(String id, String account);

}
