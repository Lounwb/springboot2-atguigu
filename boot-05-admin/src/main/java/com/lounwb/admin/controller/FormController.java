package com.lounwb.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
public class FormController {
    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestPart("avatar") MultipartFile avatar,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {

        log.info("上传的信息：email:{},username:{},avatar:{},photos:{}",
                email,username,avatar.getSize(),photos.length);
        if (!avatar.isEmpty()) {
            String originalFilename = avatar.getOriginalFilename();
            avatar.transferTo(new File("E:/springFile/"+originalFilename));
        }
        if(photos.length > 0){
            for (MultipartFile photo : photos) {
                String originalFilename = photo.getOriginalFilename();
                photo.transferTo(new File("E:/springFile/"+originalFilename));
            }
        }
        return "redirect:/main.html";
    }
}
