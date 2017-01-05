package jp.yuudachi.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.yuudachi.nsfw.role.dao.RoleDao;
import jp.yuudachi.nsfw.role.entity.Role;
import jp.yuudachi.nsfw.role.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id);
	}

	@Override
	public Role findObjectById(Serializable id) {
		return roleDao.findObjectById(id);
	}

	@Override
	public List<Role> findObjects() {
		return roleDao.findObjects();
	}

}
