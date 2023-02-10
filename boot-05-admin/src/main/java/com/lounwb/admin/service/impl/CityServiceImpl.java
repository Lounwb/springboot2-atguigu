package com.lounwb.admin.service.impl;

import com.lounwb.admin.bean.City;
import com.lounwb.admin.mapper.CityMapper;
import com.lounwb.admin.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityMapper cityMapper;
    public City getById(Integer id){
        return cityMapper.getById(id);
    }
    public void insert(City city){
        cityMapper.insert(city);
    }
}
