package com.lounwb.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lounwb.admin.bean.User;
import com.lounwb.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Controller
public class TableController {
    @Autowired
    UserService userService;
    @GetMapping("/basic_table")
    public String basic_table(){

        return "table/basic_table";
    }
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn", defaultValue = "1") Integer pn,
                             RedirectAttributes ra){
        userService.removeById(id);
        ra.addAttribute("pn", pn);

        return "redirect:/dynamic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
//        List<User> users = Arrays.asList(new User("zhangsan", "123456"),
//                new User("lisi", "123444"),
//                new User("wangwu", "aaaa"),
//                new User("zhaoliu", "1234bbb"));
//        model.addAttribute("users", users);
       // List<User> list = userService.list();
        //如何进行分页查询
        Page<User> page = new Page<>(pn, 2);
        //分页查询对象
        Page<User> userPage = userService.page(page, null);

        model.addAttribute("page", userPage);

        return "table/dynamic_table";
    }
    @GetMapping("/responsive_table")
    public String responsive_table(){

        return "table/responsive_table";
    }
    @GetMapping("/editable_table")
    public String editable_table(){

        return "table/editable_table";
    }

}
