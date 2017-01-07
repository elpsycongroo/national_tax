package jp.yuudachi.login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import jp.yuudachi.core.constant.Constant;
import jp.yuudachi.nsfw.user.entity.User;
import jp.yuudachi.nsfw.user.service.UserService;


import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

	@Resource
	private UserService userService;
	private User user;
	private String loginResult;
	
	//跳转到登录页面
	public String toLoginUI(){
		return "loginUI";
	}
	//登录
	public String login(){
		if(user != null){
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())){
				//根据用户账号密码查询用户列表
				List<User> list = userService.findUserByAccountAndPwd(user.getAccount(),user.getPassword());
				if(list != null && list.size() > 0){
					//1.1 根据用户ID查询该用户的所有角色
					user.setUserRoles(userService.getUserRolesByUserId(user.getId()));
					//登陆成功
					//1.2将用户信息保存到session中
					User user = list.get(0);
					ServletActionContext.getRequest().getSession().setAttribute(Constant.USER, user);
					//1.3将用户登录记录到相关日志文件
					Log log2 = LogFactory.getLog(getClass());
					log2.info("用户名为：" + user.getName() + "的用户登陆了系统。");
					//1.4重定向到首页
					return "home";
				}else{
					//登陆失败
					loginResult = "账号或密码不正确！";
				}
			}else{
				loginResult = "账号密码不能为空";
			}
		}else{
			loginResult = "请输入账号密码";
		}
		return toLoginUI();
	}
	//退出、注销
	public String logout(){
		//清除session中的用户信息
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
		return toLoginUI();
	}
	//跳转到没有权限提示页面
	public String toNoPermissionUI(){
		return "noPermissionUI";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getLoginResult() {
		return loginResult;
	}
	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}
	
}
