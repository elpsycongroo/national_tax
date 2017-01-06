package jp.yuudachi.nsfw.role.entity;

import java.io.Serializable;

/**
 * 联合主键类
 * @author Administrator
 *
 */
public class RolePrivilegeId implements Serializable{
	
	private String code;
	private Role role;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public RolePrivilegeId(Role role,String code) {
		super();
		this.code = code;
		this.role = role;
	}
	public RolePrivilegeId() {
	}
	
	
}
