package jp.yuudachi.nsfw.home.action;

import jp.yuudachi.core.action.BaseAction;

public class HomeAction extends BaseAction{
	
	//跳转到纳税服务系统首页
	public String frame(){
		return "frame";
	}
	//跳转到纳税服务系统首页-顶部
	public String top(){
		return "top";
	}
	//跳转到纳税服务系统首页-左面菜单
	public String left(){
		return "left";
	}
	
}