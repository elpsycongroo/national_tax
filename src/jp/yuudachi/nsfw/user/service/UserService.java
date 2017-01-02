package jp.yuudachi.nsfw.user.service;

import java.io.Serializable;
import java.util.List;

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
}
