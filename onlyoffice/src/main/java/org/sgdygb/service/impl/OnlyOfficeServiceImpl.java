package org.sgdygb.service.impl;

import java.io.IOException;

import org.sgdygb.entity.Conversion;
import org.sgdygb.service.OnlyOfficeService;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

/**
 * OnlyOfficeServiceImpl
 *
 * @author VoidAndNullity
 * @date 2023/04/10
 */
@Service
public class OnlyOfficeServiceImpl implements OnlyOfficeService {

    public static void main(String[] args) {
        // ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<String, String>();
        // String editorKey = UUID.randomUUID().toString();
        // String val = cache.putIfAbsent(editorKey, data);
        // if (val != null) {
        // data = val;
        // }
    }

    @Override
    public String wordToPdf() {
        // 创建 ObjectMapper 用于解析 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().build();
        Conversion conversion = new Conversion();
        conversion.setAsync(false);
        conversion.setFiletype("docx");
        conversion.setKey(String.valueOf(System.currentTimeMillis()));
        conversion.setOutputtype("pdf");
        conversion.setUrl("http://192.168.192.1:8181/111.docx");
        String s = "";
        try {
            s = objectMapper.writeValueAsString(conversion);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 创建请求体，携带 JSON 参数
        RequestBody requestBody = RequestBody.create(s, MediaType.parse("application/json; charset=utf-8"));
        // 创建请求
        Request request = new Request.Builder().url("http://192.168.192.100:7999/ConvertService.ashx")
            .addHeader("Accept", "application/json").addHeader("Accept-Charset", "charset=utf-8").post(requestBody)
            .build();
        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // 解析json 获取结果
            // JsonNode jsonNode = objectMapper.readTree(response.body().string());
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
