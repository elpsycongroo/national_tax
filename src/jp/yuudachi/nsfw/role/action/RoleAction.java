package jp.yuudachi.nsfw.role.action;

import jp.yuudachi.core.action.BaseAction;
import jp.yuudachi.core.constant.Constant;
import jp.yuudachi.core.util.QueryHelper;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;

import jp.yuudachi.nsfw.info.entity.Info;
import jp.yuudachi.nsfw.role.entity.Role;
import jp.yuudachi.nsfw.role.entity.RolePrivilege;
import jp.yuudachi.nsfw.role.entity.RolePrivilegeId;
import jp.yuudachi.nsfw.role.service.RoleService;

public class RoleAction extends BaseAction {

	@Resource
	private RoleService roleService;
	private Role role;
	private String[] privilegeIds;
	private String strName;

	// 列表页面
	public String listUI() throws Exception {
		ActionContext.getContext().getContextMap()
		.put("privilegeMap", Constant.PRIVILEGE_MAP);
		QueryHelper queryHelper = new QueryHelper(Role.class, "r");
		List<Object> parameters = new ArrayList<Object>();
		try {
			if(role != null){
				if(StringUtils.isNotBlank(role.getName())){
					role.setName(URLDecoder.decode(role.getName(), "utf-8"));
					//按照条件查询
					queryHelper.addCondition("r.name like ?", "%" + role.getName() +"%");
				}
			}
			pageResult = roleService.getPageResult(queryHelper,getPageNo(),getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		// 加载权限集合
		ActionContext.getContext().getContextMap()
				.put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role,
								privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		ActionContext.getContext().getContextMap()
		.put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			strName = role.getName();
			role = roleService.findObjectById(role.getRoleId());
			//进行回显处理
			if(role.getRolePrivileges() != null){
				privilegeIds = new String[role.getRolePrivileges().size()];
				int i = 0;
				//【注意】此处需要加载相关权限信息 如果rolePrivileges为懒加载则不会加载相关属性
				//因此需要禁用懒加载
				for(RolePrivilege rp : role.getRolePrivileges()){
					privilegeIds[i++] = rp.getId().getCode();
				}
			}
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		try {
			if (role != null) {
				// 处理权限保存
				if (privilegeIds != null) {
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0; i < privilegeIds.length; i++) {
						set.add(new RolePrivilege(new RolePrivilegeId(role,
								privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

}
