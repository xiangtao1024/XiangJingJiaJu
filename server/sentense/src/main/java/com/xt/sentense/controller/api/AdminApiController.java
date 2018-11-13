package com.xt.sentense.controller.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Const;
import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.Admin;
import com.xt.sentense.entity.AdminRepository;
/**
 * 管理员api接口类
 * @author XiangTao
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminApiController {
	@Autowired
	private AdminRepository adminRepository;
	
	@RequestMapping("/login.api")
	public Res login(String username, String password, HttpSession session){
		List<Admin> admin = adminRepository.findByUsername(username);
		if(admin != null && admin.size() > 0){
			Admin dbAdmin = admin.get(0);
			if(dbAdmin.getPassword().equals(password)){
				session.setAttribute(Const.ADMIN_LOGIN, dbAdmin);
				return Res.NEW().code(Res.SUCCESS).msg("登录成功");
			}
		}
		return Res.NEW().code(Res.ERROR).msg("登录失败");
	}
}
