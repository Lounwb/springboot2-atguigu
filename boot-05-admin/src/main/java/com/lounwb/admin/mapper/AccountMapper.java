package com.lounwb.admin.mapper;

import com.lounwb.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AccountMapper {
    public Account getActById(Integer id);
}
