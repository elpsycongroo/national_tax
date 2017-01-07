package jp.yuudachi.core.permission;

import jp.yuudachi.nsfw.user.entity.User;

public interface PermissionCheck {
	
	/**
	 * 判断用户是否有访问子版块的权限
	 * @param user 用户
	 * @param code 子系统的权限提示符
	 * @return true or false
	 */
	boolean isAccessible(User user, String code);

}
