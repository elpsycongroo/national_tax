package jp.yuudachi.nsfw.user.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.yuudachi.nsfw.user.dao.UserDao;
import jp.yuudachi.nsfw.user.entity.User;
import jp.yuudachi.nsfw.user.service.UserService;
/**
 * User业务实现类
 * @author Administrator
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(Serializable id) {
		userDao.delete(id);
	}

	@Override
	public User findObjectById(Serializable id) {
		return userDao.findObjectById(id);
	}

	@Override
	public List<User> findObjects() {
		return userDao.findObjects();
	}

}
