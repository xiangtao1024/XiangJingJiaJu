package com.xt.sentense.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.springframework.stereotype.Component;

import com.xt.sentense.constant.Const;
import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.Admin;
/**
 * 权限控制过滤器
 * 通过url判断权限
 * @author XiangTao
 *
 */
@Component
public class PermissionFilter implements Filter{
	//配置不需要验证的路径或路径的后缀名
	private List<String> noFilterUrl = new ArrayList<>();
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		/**
		 * 登录，图片，css,js等资源不需要验证
		 */
		noFilterUrl.add("/admin/login.html");
		noFilterUrl.add(".png");
		noFilterUrl.add(".jpeg");
		noFilterUrl.add(".jpg");
		noFilterUrl.add(".css");
		noFilterUrl.add(".js");
		noFilterUrl.add(".ico");
		noFilterUrl.add(".woff");
		noFilterUrl.add(".ttf");
	}
	/**
	 * 先检查此次请求是否需要验证，需要验证的话，根据请求调用网页请求
	 * 验证或者api接口进行验证 。。。
	 * 算了，这个注释不会写了...直接看代码应该能看懂吧
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getServletPath();
		if(checkUrl(url)){ //检查url
			chain.doFilter(req, resp);
		}else if(url.endsWith(".html")){ //网页权限验证
			if(checkSession(request)){
				chain.doFilter(req, resp);
			}else{
				request.getRequestDispatcher("/admin/login.html").forward(req, resp);
			}
		}else if(url.endsWith(".api")){ //api接口验证
			String key = request.getHeader(Const.AUTH_KEY);
			String sign = request.getHeader(Const.AUTH_SIGN);
			if(checkApi(key, sign)){
				chain.doFilter(req, resp);
			}else{
				resp.setCharacterEncoding("UTF-8");  
				resp.setContentType("application/json; charset=utf-8");
				PrintWriter out = resp.getWriter();
				if(out != null){
					out.append(Json.toJson(Res.NEW().code(Res.ERROR).msg("api授权失败")));
					out.close();
				}
			}
		}else{
			request.getRequestDispatcher("/admin/login.html").forward(req, resp);
		}
	}
	
	/**
	 * 检查api接口
	 * @param req
	 * @param resp
	 * @param chain
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private boolean checkApi(String key, String sign) throws IOException, ServletException {
		// TODO Auto-generated method stub
		long time = 1000;
		key = Lang.md5(Lang.md5(key + time) + time);
		if(key.equals(sign)){
			return true;
		}
		return false;
	}

	/**
	 * 检查session
	 * @param req
	 * @param resp
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	private boolean checkSession(HttpServletRequest request) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute(Const.ADMIN_LOGIN);
		if(admin != null){
			return true;
		}
		return false;
	}
	/**
	 * 检查URL
	 * @param req
	 * @param resp
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	private boolean checkUrl(String url) throws IOException, ServletException {
		// TODO Auto-generated method stub
		for(String u : noFilterUrl){
			if(url.contains(u)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
