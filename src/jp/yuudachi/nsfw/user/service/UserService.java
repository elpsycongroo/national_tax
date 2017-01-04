package jp.yuudachi.nsfw.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletOutputStream;

import jp.yuudachi.nsfw.user.entity.User;

public interface UserService {
	// save
	public void save(User entity);

	// update
	public void update(User entity);

	// delete by id
	public void delete(Serializable id);

	// find by id
	public User findObjectById(Serializable id);

	// find list
	public List<User> findObjects();
	//导出用户列表
	public void exportExcel(List<User> userList,
			ServletOutputStream outputStream);
	//导入用户列表
	public void importExcel(File userExcel, String userExcelFileName);
	//根据账号名和用户ID查询用户
	public List<User> findUserByAccountAndId(String id, String account);
}
