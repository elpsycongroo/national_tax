package jp.yuudachi.nsfw.role.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable {
	
	//联合主键类 需要实现serializable接口 重写hashcode equals方法
	private RolePrivilegeId id;

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}

	public RolePrivilege() {
	}
	
}
