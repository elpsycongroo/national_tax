package jp.yuudachi.nsfw.role.dao;

import jp.yuudachi.core.dao.BaseDao;
import jp.yuudachi.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {

	void deleteRolePrivilegeByRoleId(String roleId);

}
