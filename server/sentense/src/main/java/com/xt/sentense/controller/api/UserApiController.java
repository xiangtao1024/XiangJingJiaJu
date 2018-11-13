package com.xt.sentense.controller.api;

import java.util.Date;
import java.util.List;

import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xt.sentense.constant.Res;
import com.xt.sentense.entity.User;
import com.xt.sentense.entity.UserRepository;
import com.xt.sentense.utils.BaseUtil;
/**
 * 用户api接口类
 * @author XiangTao
 *
 */
@RestController
@RequestMapping("/user")
public class UserApiController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path={"/add.api", "/regist.api"})
	public Res add(User user){
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setSalt(R.sg(9).next());
		try{
			user = userRepository.saveAndFlush(user);
			if(user != null){
				return Res.NEW().code(Res.SUCCESS).msg("注册成功").data(user);
			}else{
				return Res.NEW().code(Res.ERROR).msg("注册失败").data(user);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getMessage().matches(".*constraint.*UK.*")){
				return Res.NEW().code(Res.ERROR).msg("注册失败: 该用户已经存在");
			}else{
				return Res.NEW().code(Res.ERROR).msg("注册失败: " + e.getMessage());
			}
		}
	}
	
	@RequestMapping(path={"/login.api"})
	public Res login(User user){
		List<User> users = userRepository.findByPhone(user.getPhone());
		if(users == null || users.size() < 1){
			return Res.NEW().code(Res.ERROR).msg("登录失败， 该手机号还未注册");
		}
		User dbUser = users.get(0);
		if(Strings.equals(BaseUtil.jiamiPassword(user.getPassword(), user.getSalt()), dbUser.getPassword())){
			return Res.NEW().code(Res.SUCCESS).msg("登录成功");
		}else{
			return Res.NEW().code(Res.ERROR).msg("登录失败， 账号密码不正确");
		}
	}
	
	@RequestMapping("/update.api")
	public Res update(User user){
		User c = userRepository.getOne(user.getId());
		if(c == null){
			return Res.NEW().code(Res.ERROR).msg("数据库没有该条记录");
		}
		c.setIcon(user.getIcon());
		c.setName(user.getName());
		c.setPassword(user.getPassword());
		c.setNickname(user.getNickname());
		c.setPhone(user.getPhone());
		c.setPhoneState(user.getPhoneState());
		c.setUpdateTime(new Date());
		try{
			c = userRepository.saveAndFlush(c);
			if(c != null){
				return Res.NEW().code(Res.SUCCESS).msg("编辑成功").data(c);
			}else{
				return Res.NEW().code(Res.ERROR).msg("编辑失败").data(c);
			}
		}catch(Exception e){
			e.printStackTrace();
			return Res.NEW().code(Res.ERROR).msg("编辑失败: " + e.getMessage());
		}
	}
	@RequestMapping("/delete.api")
	public Res delete(Long id){
		try{
			userRepository.deleteById(id);
			return Res.NEW().code(Res.SUCCESS).msg("删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return Res.NEW().code(Res.ERROR).msg("删除失败: " + e.getMessage());
		}
	}
	
	@RequestMapping("/finds.api")
	public Res finds(int page, int size){
		Pageable p = new PageRequest(page - 1, size);
		Page<User> datas = userRepository.findAll(p);
		return Res.NEW().code(Res.SUCCESS).msg("ok").data(datas);
	}
	
	@RequestMapping("/findById.api")
	public Res findById(Long id){
		try{
			User u = userRepository.getOne(id);
			if(u != null){
				return Res.NEW().code(Res.SUCCESS).msg("Ok").data(u);
			}else{
				return Res.NEW().code(Res.ERROR).msg("获取失败,没有此记录");
			}
		}catch(Exception e){
			return Res.NEW().code(Res.ERROR).msg("获取失败: " + e.getMessage());
		}
	}
}
