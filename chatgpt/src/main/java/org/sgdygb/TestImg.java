package org.sgdygb;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

/**
 * @author 提笔忘字的帝国
 */
public class TestImg {
    /** API秘钥 添加自己的秘钥 */
    private static final String KEY = "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";
    /** url */
    private static final String URL = "https://api.openai.com/v1/images/generations";
    /** 配置代理服务的ip 根据自己实际情况配置 */
    private static final String HOST = "127.0.0.1";
    /** 配置代理服务的端口 根据自己实际情况配置 */
    private static final int PORT = 7890;

    public static void main(String[] args) throws JsonProcessingException {
        // 输入内容
        String content = "生成功夫熊猫电影里的熊猫";
        // 创建 ObjectMapper 用于解析 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        Images images = new Images();
        // 生成图像的描述
        images.setPrompt(content);
        // 生成图像的数量
        images.setN(1);
        // 生成图像的尺寸
        images.setSize("1024x1024");
        // 配置代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOST, PORT));
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();
        // 创建请求体，携带 JSON 参数
        RequestBody requestBody = RequestBody.create(objectMapper.writeValueAsString(images),
            MediaType.parse("application/json; charset=utf-8"));
        // 创建请求
        Request request =
            new Request.Builder().url(URL).addHeader("Authorization", "Bearer ".concat(KEY)).post(requestBody).build();
        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // 解析json 获取结果
            JsonNode jsonNode = objectMapper.readTree(response.body().string());
            System.out.println(jsonNode.get("data").get(0).get("url").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
