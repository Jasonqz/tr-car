package com.example.vehicle_server.controller;

import com.example.vehicle_server.pojo.DataEntity;
import com.example.vehicle_server.service.DataService;
import com.example.vehicle_server.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/vehicle")
public class ScreenController {
    @Autowired
    private DataService countService;

    //获取数据库总数
    @RequestMapping("/getCounts")
    public Result getData(){
        if(countService.getCounts() != -1) {
            return Result.success(countService.getCounts());
        } else {
            return Result.error("结果出错");
        }
    }

    //获取数据库内的所有时间
    @RequestMapping("/getTimes")
    public Result getTimes(){
        return Result.success(countService.getTimes());
    }

    //根据时间获取数据
    @RequestMapping( "/getDataByTime")
    public Result getDataByTime(@RequestBody String selectTime){
        System.out.println(selectTime);
        List<DataEntity> data = countService.getByTime(selectTime);
        return Result.success(data);
    }
}
