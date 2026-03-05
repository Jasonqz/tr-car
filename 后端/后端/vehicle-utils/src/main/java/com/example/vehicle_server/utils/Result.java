package com.example.vehicle_server.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果封装类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    //响应码，1代表成功；0代表失败
    private Integer code;

    //提示信息
    private String msg;

    //返回的数据
    private Object data;

//    public Result() {
//    }
//
//    public Result(Integer code, String msg, Object data) {
//        this.code = code;
//        this.msg = msg;
//        this.data = data;
//    }
//
//    public Integer getCode() {
//        return code;
//    }
//
//    public void setCode(Integer code) {
//        this.code = code;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
    public static Result success(Object data) {
        return  new Result(1, "success", data);
    }

    public static Result success() {
        return  new Result(1, "success", null);
    }

    public static Result error(String msg) {
        return  new Result(0, msg, null);
    }
//
//    @Override
//    public String toString() {
//        return "Result{" +
//                "code=" + code +
//                ", msg='" + msg + '\'' +
//                ", data=" + data +
//                '}';
//    }
}
