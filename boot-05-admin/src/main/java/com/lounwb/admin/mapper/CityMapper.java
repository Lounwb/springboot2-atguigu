package com.lounwb.admin.mapper;

import com.lounwb.admin.bean.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {
    @Select("select * from city where id=#{id}")
    public City getById(Integer id);

    public void insert(City city);
}
