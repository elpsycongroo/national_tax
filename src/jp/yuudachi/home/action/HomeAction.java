package jp.yuudachi.home.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jp.yuudachi.core.util.QueryHelper;
import jp.yuudachi.nsfw.user.entity.User;
import jp.yuudachi.nsfw.user.service.UserService;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport{
	
	@Resource
	private UserService userService;
	
	private Map<String, Object> returnMap;
	
	//跳转到首页
	public String execute(){
		return "home";
	}
	//跳转到我要投诉
	public String complainAddUI(){
		return "complainAddUI";
	}
	public void getUserJson(){
		try {
			//1.获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if( StringUtils.isNotBlank(dept) ){
				//2.根据部门查询用户列表
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept = ?", dept);
				List<User> userList = userService.findObjects(queryHelper);
				//創建json對象
				JSONObject json = new JSONObject();
				json.put("msg", "success");
				json.accumulate("userList", userList);
				//3.输出用户列表 以json格式字符串形式输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(json.toString().getBytes("utf-8"));
				outputStream.close();		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public String getUserJsonByFrame(){
		try {
			//1.获取部门
			String dept = ServletActionContext.getRequest().getParameter("dept");
			if( StringUtils.isNotBlank(dept) ){
				//2.根据部门查询用户列表
				QueryHelper queryHelper = new QueryHelper(User.class, "u");
				queryHelper.addCondition("u.dept = ?", dept);
				returnMap = new HashMap<String, Object>();
				returnMap.put("msg", "success");
				returnMap.put("userList", userService.findObjects(queryHelper));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public Map<String, Object> getReturnMap() {
		return returnMap;
	}
	public void setReturnMap(Map<String, Object> returnMap) {
		this.returnMap = returnMap;
	}
}
