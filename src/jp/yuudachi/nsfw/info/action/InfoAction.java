package jp.yuudachi.nsfw.info.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import jp.yuudachi.core.action.BaseAction;
import jp.yuudachi.core.constant.Constant;
import jp.yuudachi.nsfw.info.entity.Info;
import jp.yuudachi.nsfw.info.service.InfoService;

public class InfoAction extends BaseAction {

	@Resource
	private InfoService infoService;
	private List<Info> infoList;
	private Info info;
	private String[] privilegeIds;

	// 列表页面
	public String listUI() throws Exception {
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Constant.PRIVILEGE_MAP);
		try {
			infoList = infoService.findObjects();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Constant.PRIVILEGE_MAP);
		//给jsp页面传时间 这里会报空指针（没有实例化）
		//实例化
		info = new Info();
		info.setCreateTime(new Timestamp(new Date().getTime()));
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (info != null) {
				infoService.save(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		// 加载分类集合
		ActionContext.getContext().getContextMap()
				.put("infoTypeMap", Constant.PRIVILEGE_MAP);
		if (info != null && info.getInfoId() != null) {
			info = infoService.findObjectById(info.getInfoId());
		}
		return "editUI";
	}

	// 保存编辑
	public String edit() {
		try {
			if(info != null){
				infoService.update(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (info != null && info.getInfoId() != null) {
			infoService.delete(info.getInfoId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			for (String id : selectedRow) {
				infoService.delete(id);
			}
		}
		return "list";
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public List<Info> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<Info> infoList) {
		this.infoList = infoList;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String[] getPrivilegeIds() {
		return privilegeIds;
	}

	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}

}
