package com.hx.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hx.bean.User;
import com.hx.service.LoginService;

@RestController
public class LoginController {
	@Value("${version}")
	private String version;
	@Value("${function}")
	private String function;
	
	@Autowired
    private LoginService loginService;
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView  modelAndView) {
		modelAndView.setViewName("/login/login.html");
        return modelAndView;
	}
	
	@RequestMapping("/logout")
	public String login() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
        return "logout";
	}

    @RequestMapping("/loginAuth")
    public String loginAuth(User user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
            //IncorrectCredentialsException
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return "該当ユーザIDがありません。";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return "該当ユーザIDのパスワードが間違っています。";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "該当ユーザIDのパスワードが間違っています。";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "没有权限";
        }
        return "success";
    }

//    @RequiresRoles("admin")
//    @RequiresPermissions("add")
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView  modelAndView) {
		modelAndView.setViewName("/index/index.html");
        return modelAndView;
	}
    
    @RequestMapping("/getUser")
    public User getUser() {
    	User user = loginService.getUser();
    	return user;
	}
    
    @RequestMapping("/getLocalTime")
    public String getLocalTime() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分ss秒");
    	return sdf.format(new Date());
	}
    
    @RequestMapping("/getVersion")
    public Map<String, String> getVersion() {
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("version", version);
    	map.put("function", function);
    	return map;
	}
}
