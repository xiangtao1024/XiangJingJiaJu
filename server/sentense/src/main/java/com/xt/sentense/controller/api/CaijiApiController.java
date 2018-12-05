package com.xt.sentense.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Res;
import com.xt.sentense.service.JuziDaquanCaijiService;

/**
 * 采集module api
 * @author XiangTao
 *
 */
@RestController
@RequestMapping("/caiji")
public class CaijiApiController {
	@Autowired
	private JuziDaquanCaijiService juziDaquanCaijiService;
	
	@RequestMapping("/start.api")
	public Res start(){
		juziDaquanCaijiService.start();
		return Res.NEW().code(Res.SUCCESS).msg("调用采集接口");
	}
	
	@RequestMapping("/getInfo.api")
	public Res getInfo(){
		return Res.NEW().code(Res.SUCCESS).msg("调用信息接口")
				.data(juziDaquanCaijiService.getInfo());
	}
}
