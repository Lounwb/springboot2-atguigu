package com.lounwb.admin.controller;

import com.lounwb.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    /**
     * 登录页
     * @return
     */
    @GetMapping({"/","/login"})
    public String loginPage(){
        return "login";
    }

    /**
     * 提交表单，登录，重定向到main.html请求
     * @return
     */
    @PostMapping("/login")
    public  String main(User user, HttpSession session, Model model){
        if(!StringUtils.isEmpty(user.getUsername()) && !StringUtils.isEmpty(user.getPassword())){
            session.setAttribute("user", user);
            //登陆成功，重定向到main页面，防止表单重复提交
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg", "账号密码错误");
            return "login";
        }

    }

    /**
     * 接受main.html请求，映射到templates/main.html
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session, Model model){
        Object user = session.getAttribute("user");
        if (user != null) {
            return "main";
        }else {
            //返回登录页面
            model.addAttribute("msg", "请先登录");
            return "login";
        }
    }
}
