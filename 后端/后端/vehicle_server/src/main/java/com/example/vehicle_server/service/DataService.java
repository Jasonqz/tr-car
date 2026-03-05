package com.example.vehicle_server.service;

import com.example.vehicle_server.pojo.DataEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface DataService {

    //获取数据量总数
    public Integer getCounts();

    //根据时间获取对应的对象
    public List<DataEntity> getByTime(String time);

    //获取数据库中所有时间
    public Set<String> getTimes();
}
