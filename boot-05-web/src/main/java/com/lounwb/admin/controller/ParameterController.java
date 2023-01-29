package com.lounwb.admin.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ParameterController {
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String, Object> getCar(@PathVariable("id") Integer id,
                                      @PathVariable("username") String username,
                                      @PathVariable Map<String, String> pv,
                                      @RequestHeader("User-Agent") String userAgent,
                                      @RequestHeader Map<String, String> headers) {
        Map<String, Object> map = new HashMap<>();

        map.put("id", id);
        map.put("username", username);
        map.put("pv", pv);
        map.put("userAgent", userAgent);
        map.put("headers", headers);
        return map;
    }
    @PostMapping("/save")
    public Map<String, Object> postMethod(@RequestBody String content){
        Map<String, Object> map = new HashMap<>();

        map.put("content", content);
        return map;
    }
}
