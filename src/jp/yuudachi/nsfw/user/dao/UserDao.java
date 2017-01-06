package jp.yuudachi.nsfw.user.dao;

import java.util.List;

import jp.yuudachi.core.dao.BaseDao;
import jp.yuudachi.nsfw.user.entity.User;
import jp.yuudachi.nsfw.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {
	//根据用户名和ID查询用户
	List<User> findUserByAccountAndId(String id, String account);
	//保存用户角色
	void saveUserRole(UserRole userRole);
	//根据用户ID删除用户的所有角色
	void deleteUserRoleByUserId(String id);
	//根据用户ID获取用户的所有角色
	List<UserRole> getUserRolesByUserId(String id);

}
