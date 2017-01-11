package jp.yuudachi.nsfw.role.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import jp.yuudachi.core.service.impl.BaseServiceImpl;
import jp.yuudachi.nsfw.role.dao.RoleDao;
import jp.yuudachi.nsfw.role.entity.Role;
import jp.yuudachi.nsfw.role.service.RoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}
	
	@Override
	public void update(Role role) {
		//避免hibernate在update的时候出现问题
		//删除该角色对应的所有权限
		//(方法二：新增一个逻辑标签来判断是否显示 此处略）
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		//更新角色及其权限
		roleDao.update(role);
	}

}
