package com.example.vehicle_server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataEntity {

    private int id;
    private String frontImgPath;
    private String driverImgPath;
    private String carImgPath;
    private int speed;
    private int acceleration;
    private int temperature;
    private int pressure;
    private int height;
    private LocalDateTime time;
}
