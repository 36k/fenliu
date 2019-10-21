package com.hlau.fenliu.Utils;

import java.util.LinkedHashMap;

public class RestUtil {
    public static LinkedHashMap SUCCESS(Object data){
        LinkedHashMap res=new LinkedHashMap();
        res.put("code",20000);
        res.put("msg","success");
        res.put("data",data);
        return res;
    }
    public static LinkedHashMap ERROR(int code,String msg){
        LinkedHashMap res=new LinkedHashMap();
        res.put("code",code);
        res.put("msg",msg);
        return res;
    }
}
