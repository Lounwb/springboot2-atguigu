package com.lounwb.admin.controller;

import com.lounwb.admin.bean.Account;
import com.lounwb.admin.bean.City;
import com.lounwb.admin.bean.User;
import com.lounwb.admin.service.AccountService;
import com.lounwb.admin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    AccountService accountService;
    @Autowired
    CityService cityService;
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
        /*Object user = session.getAttribute("user");
        if (user != null) {
            return "main";
        }else {
            //返回登录页面
            model.addAttribute("msg", "请先登录");
            return "login";
        }*/
        return "main";
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");

        return "redirect:/";
    }
    @ResponseBody
    @GetMapping("/query")
    public String queryFromDB(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from owner", Long.class);
        return aLong.toString();
    }
    @ResponseBody
    @GetMapping("/act")
    public Account getActById(@RequestParam("id") Integer id){
        return accountService.getActById(id);
    }
    @ResponseBody
    @GetMapping("/city")
    public City getCityById(@RequestParam("id") Integer id){
        return cityService.getById(id);
    }
    @ResponseBody
    @PostMapping("/city")
    public void saveCity(City city){
        cityService.insert(city);
    }
}
