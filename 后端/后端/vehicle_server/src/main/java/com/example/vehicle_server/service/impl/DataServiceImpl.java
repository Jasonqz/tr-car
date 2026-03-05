package com.example.vehicle_server.service.impl;

import com.example.vehicle_server.mapper.DataMapper;
import com.example.vehicle_server.pojo.DataEntity;
import com.example.vehicle_server.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//@Transactional 事务管理，一般加在业务层对数据增删改的方法上
//@Transactional(rollbackFor = Exception.class) 所有异常都回滚

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private DataMapper countMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer getCounts() {
        List<String> tableNames = countMapper.getAllTableNames();
        int count = 0;
        for (String tableName : tableNames) {
            count += countMapper.getCountsByName(tableName);
        }
        return count;
    }

    @Override
    public List<DataEntity> getByTime(String time) {
        return countMapper.getByTime(time);
    }

    @Override
    public Set<String> getTimes() {
        Set<String> times = new TreeSet<>();
        List<String> tableNames = countMapper.getAllTableNames();
        for (String tableName : tableNames) {
            List<LocalDateTime> localTimes = countMapper.getTimesByTableName(tableName);
            for (LocalDateTime localTime : localTimes) {
                String[] s = localTime.toString().split("T");
                times.add(s[0]);
            }
        }
        return times;
    }
}
