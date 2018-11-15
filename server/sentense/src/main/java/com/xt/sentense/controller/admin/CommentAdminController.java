package com.xt.sentense.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 场景后台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {
	@RequestMapping("/index.html")
	public String index(){
		return "admin/comment/index";
	}
}
