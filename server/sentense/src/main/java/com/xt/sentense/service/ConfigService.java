package com.xt.sentense.service;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.xt.sentense.constant.Const;

/**
 * 配置应用的服务类
 * @author XiangTao
 *
 */
@Component
public class ConfigService {
	/**
	 * 文件上传缓存目录设置，不配置的话使用默认的会被清除，然后报错
	 * (Failed to parse multipart servlet request; nested exception is)
	 * @return
	 */
	@Bean
    MultipartConfigElement multipartConfigElement() {
       MultipartConfigFactory factory = new MultipartConfigFactory();
       factory.setLocation(Const.APP_FILE + "/temp");
       return factory.createMultipartConfig();
    }
}
