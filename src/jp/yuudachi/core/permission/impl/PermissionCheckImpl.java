package jp.yuudachi.core.permission.impl;

import java.util.List;

import javax.annotation.Resource;

import jp.yuudachi.core.permission.PermissionCheck;
import jp.yuudachi.nsfw.role.entity.Role;
import jp.yuudachi.nsfw.role.entity.RolePrivilege;
import jp.yuudachi.nsfw.user.entity.User;
import jp.yuudachi.nsfw.user.entity.UserRole;
import jp.yuudachi.nsfw.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {

	@Resource
	private UserService userService;
	
	@Override
	public boolean isAccessible(User user, String code) {
		//1.获取用户的所有角色
		List<UserRole> list = user.getUserRoles();
		if(list == null){
			list = userService.getUserRolesByUserId(user.getId());
		}
		//2.根据每个角色对应的所有权限进行对比
		if(list != null && list.size() > 0){
			for(UserRole ur : list){
				Role role = ur.getId().getRole();
				for(RolePrivilege rp : role.getRolePrivileges()){
					//对比是否有code给的权限
					if(code.equals(rp.getId().getCode())){
						//如有权限 返回true
						return true;
					}
				}
			}
		}
		return false;
	}

}
