package com.lounwb.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lounwb.admin.bean.User;
import com.lounwb.admin.mapper.UserMapper;
import com.lounwb.admin.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
