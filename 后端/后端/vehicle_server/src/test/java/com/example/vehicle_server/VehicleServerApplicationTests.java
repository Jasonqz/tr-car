package com.example.vehicle_server;

import com.example.vehicle_server.pojo.DataEntity;
import com.example.vehicle_server.service.DataService;
import com.example.vehicle_server.utils.Result;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

@SpringBootTest
class VehicleServerApplicationTests {

    @Autowired
    private DataService countService;

    @Test
    void contextLoads() {
    }

    @Test
    public void getData(){
        System.out.println(countService.getCounts());
    }

    @Test
    public void getData2(){
        LocalDate today = LocalDate.now();
        System.out.println(today);
        List<DataEntity> datalist = countService.getByTime(today.toString());
        if(datalist != null && !datalist.isEmpty()){
            for (DataEntity data : datalist) {
                System.out.println(data);
            }
        } else {
            System.out.println(0);
        }
    }

    @Test
    public void getTimes(){
        Set<String> times = countService.getTimes();
        System.out.println(times);
    }

    @Test
    public void getDataByTime(){
        List<DataEntity> data = countService.getByTime("2024-09-04");
        System.out.println(data);;
    }

    @Test
    public void testGenJwt(){
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", 1);
        claims.put("name", "Tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "vehicle")//签名算法
                .setClaims(claims)//自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置有效期为1h
                .compact();
        System.out.println(jwt);
    }

}
