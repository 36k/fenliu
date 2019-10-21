package com.hlau.fenliu.Utils;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtil {
    public static void RESPONSE(HttpServletResponse response,int code, String msg) throws IOException {
        response.setContentType(String.valueOf(MediaType.APPLICATION_JSON_UTF8));
        response.setStatus(200);
        Map<String,Object> res=new LinkedHashMap<>();
        res.put("code",code);
        res.put("msg",msg);
        String resText = JSONUtils.toJSONString(res);
        PrintWriter out=response.getWriter();
        out.write(resText);
        out.flush();
    }
}
