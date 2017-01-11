package jp.yuudachi.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import jp.yuudachi.core.exception.ServiceException;
import jp.yuudachi.core.service.BaseService;
import jp.yuudachi.nsfw.user.entity.User;
import jp.yuudachi.nsfw.user.entity.UserRole;

public interface UserService extends BaseService<User>{

	//导出用户列表
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream);
	//导入用户列表
	public void importExcel(File userExcel, String userExcelFileName);
	//根据账号名和用户ID查询用户
	public List<User> findUserByAccountAndId(String id, String account);
	//保存用户及其对应的角色
	public void saveUserAndRole(User user, String... roleIds);
	//更新用户及其角色
	public void updateUserAndRole(User user, String... roleIds);
	//根据用户ID获取该用户对应的所有用户角色
	public List<UserRole> getUserRolesByUserId(String id);
	//根据用户账号密码查询用户列表
	public List<User> findUserByAccountAndPwd(String account, String password);
}
