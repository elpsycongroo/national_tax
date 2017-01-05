package jp.yuudachi.nsfw.role.action;

import jp.yuudachi.core.action.BaseAction;
import java.util.List;
import javax.annotation.Resource;

import jp.yuudachi.nsfw.role.entity.Role;
import jp.yuudachi.nsfw.role.service.RoleService;

public class RoleAction extends BaseAction {

	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;

	// 列表页面
	public String listUI() throws Exception {
		try {
			roleList = roleService.findObjects();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		return "addUI";
	}

	// 保存新增
	public String add() {
		return "";
	}

	// 跳转到编辑页面
	public String editUI() {
		if (role != null && role.getRoleId() != null) {
			role = roleService.findObjectById(role.getRoleId());
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		try {
			if (role != null) {

				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (role != null && role.getRoleId() != null) {
			roleService.delete(role.getRoleId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				roleService.delete(id);
			}
		}
		return "list";
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
