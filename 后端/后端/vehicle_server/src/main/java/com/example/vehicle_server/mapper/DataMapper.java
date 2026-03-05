package com.example.vehicle_server.mapper;

import com.example.vehicle_server.pojo.DataEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DataMapper {

    /*@Select("select COUNT(*) from test_table")*/
    //根据表名获取该表数据总量
    public Integer getCountsByName(String tableName);

    //查询数据库所有表名
    public List<String> getAllTableNames();

    //根据时间查询数据
    public List<DataEntity> getByTime(String time);

    //查询记录的时间
    public List<LocalDateTime> getTimesByTableName(String tableName);
}
