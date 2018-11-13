package com.xt.sentense.utils;

import org.nutz.lang.Lang;

/**
 * 基础工具包，封装一些常用的工具
 * @author XiangTao
 *
 */
public class BaseUtil {
	/**
	 * 加密密码
	 * @param password
	 * @param salt
	 * @return
	 */
	public static String jiamiPassword(String password, String salt){
		return Lang.md5(Lang.md5(password + salt) + salt);
	}
}
