package com.lounwb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestController {
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg", "转发成功...");
        request.setAttribute("code", 200);
        return "forward:/success";
    }
    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute String msg,
                       @RequestAttribute Integer code,
                       HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        Object msg1 = request.getAttribute("msg");

        map.put("msg", msg);
        map.put("msg1", msg1);
        map.put("code", code);
        return map;
    }
    // /cars/sell;low=34;brand=byd,audi,yd
    @ResponseBody
    @GetMapping("/cars/{sell}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("sell") String sell){
        Map<String, Object> map = new HashMap<>();

        map.put("low", low);
        map.put("brand", brand);
        map.put("sell", sell);
        return map;
    }
}
