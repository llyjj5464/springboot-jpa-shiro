package com.lyj.shiroweb.controller;


import com.lyj.shiroweb.dto.UserDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lyj
 * @date 2019/9/8 19:01
 */
@RestController
@RequestMapping(value = LoginController.PATH)
public class LoginController {
    public static final String PATH = "/subLogin";

    @GetMapping
    public ModelAndView toLogin(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login.html");
        return mav;
    }

    @GetMapping(value = "/403")
    public ModelAndView to403(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("403.html");
        return mav;
    }

    @PostMapping
    public String login(UserDTO userDTO){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userDTO.getUsername(),userDTO.getPassword());
        /**
         * 编程式验证权限
         * */
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            return e.getMessage();
        }
        if(subject.hasRole("admin")){
            return "具有管理员权限";
        }
        return "登入成功";
    }
    /**
     * 注解式验证权限
     * */
    @GetMapping(value = "/testAdminRole")
    @RequiresRoles("admin")
    public String testAdminRole(){
        return "admin";
    }

    @GetMapping(value = "/testAdminRole1")
    @RequiresRoles("admin1")
    public String testAdminRole1(){
        return "admin1";
    }

  /*  @GetMapping(value = "/testRole")
    @RequiresPermissions("xxx")
    public String testRole(){
        return "testRole success";
    }*/
}
