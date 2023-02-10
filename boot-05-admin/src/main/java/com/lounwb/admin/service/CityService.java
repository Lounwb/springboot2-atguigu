package com.lounwb.admin.service;

import com.lounwb.admin.bean.City;

public interface CityService {
    public City getById(Integer id);
    public void insert(City city);
}
