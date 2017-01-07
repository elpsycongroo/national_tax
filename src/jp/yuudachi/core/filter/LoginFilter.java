package jp.yuudachi.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jp.yuudachi.core.constant.Constant;
import jp.yuudachi.core.permission.PermissionCheck;
import jp.yuudachi.nsfw.user.entity.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String uri = request.getRequestURI();
		// 判断当前请求地址是否为登录的请求地址
		if (!uri.contains("sys/login_")) {
			// 非登录请求
			if (request.getSession().getAttribute(Constant.USER) != null) {
				// 已经登录 
				// 进行权限控制
				privilegeController(request, response, chain, uri);
			} else {
				//没有登录 返回登录页面
				response.sendRedirect(request.getContextPath()
						+ "/sys/login_toLoginUI.action");
			}
		} else {
			// 登录请求 直接放行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void privilegeController(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain, String uri) throws IOException, ServletException {
		// 判断是否访问纳税服务系统
		if (uri.contains("/nsfw/")) {
			// 访问纳税服务子系统 判断有没有权限
			User user = (User) request.getSession().getAttribute(Constant.USER);
			//取得一个IOC容器
			WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			PermissionCheck pc = (PermissionCheck) applicationContext.getBean("permissionCheck");
			if(pc.isAccessible(user,"nsfw")){
				//有权限 放行
				chain.doFilter(request, response);
			}else{
				//没有权限 跳转没有权限提示页面
				response.sendRedirect(request.getContextPath() + "/sys/login_toNoPermissionUI.action");
			}
		} else {
			// 没有访问纳税服务子系统
			chain.doFilter(request, response);
		}
	}
}
