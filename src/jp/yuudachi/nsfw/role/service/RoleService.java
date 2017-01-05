package jp.yuudachi.nsfw.role.service;

import java.io.Serializable;
import java.util.List;

import jp.yuudachi.nsfw.role.entity.Role;


public interface RoleService {
	// save
	public void save(Role role);

	// update
	public void update(Role role);

	// delete by id
	public void delete(Serializable id);

	// find by id
	public Role findObjectById(Serializable id);

	// find list
	public List<Role> findObjects();
}
