package com.xt.sentense.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 标签后台类
 * @author XiangTao
 *
 */
@Controller
@RequestMapping("/admin/label")
public class LabelAdminController {
	@RequestMapping("/index.html")
	public String index(){
		return "admin/label/index";
	}
}
