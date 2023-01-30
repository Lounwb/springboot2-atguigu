package com.lounwb.admin.controller;

import com.lounwb.admin.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {
    @GetMapping("/basic_table")
    public String basic_table(){

        return "table/basic_table";
    }
    @GetMapping("/dynamic_table")
    public String dynamic_table(Model model){
        List<User> users = Arrays.asList(new User("zhangsan", "123456"),
                new User("lisi", "123444"),
                new User("wangwu", "aaaa"),
                new User("zhaoliu", "1234bbb"));
        model.addAttribute("users", users);

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
