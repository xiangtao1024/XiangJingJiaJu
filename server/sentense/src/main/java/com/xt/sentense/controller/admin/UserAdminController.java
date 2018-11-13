package com.xt.sentense.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 用户后台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {
	@RequestMapping("/index.html")
	public String index(){
		return "admin/user/index";
	}
}
