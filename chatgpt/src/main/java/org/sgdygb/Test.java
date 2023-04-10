package org.sgdygb;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collections;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

/**
 * @author 提笔忘字的帝国
 */
public class Test {
    /** API秘钥 添加自己的秘钥 */
    private static final String KEY = "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";
    /** url */
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    /** 配置代理服务的ip 根据自己实际情况配置 */
    private static final String HOST = "127.0.0.1";
    /** 配置代理服务的端口 根据自己实际情况配置 */
    private static final int PORT = 7890;

    public static void main(String[] args) throws JsonProcessingException {
        // 输入内容
        String content = "您好";
        // 创建 ObjectMapper 用于解析 JSON
        ObjectMapper objectMapper = new ObjectMapper();
        Text text = new Text();
        // 设置模型
        text.setModel("gpt-3.5-turbo");
        // 值越小，生成的文本越可信，但也越无创造性 值越大，生成的文本越有创造性，但也越不可信 范围：0.0-1.0
        text.setTemperature(0.7);
        text.setMessages(Collections.singletonList(new Text.MessagesBean("user", content)));
        // 配置代理
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOST, PORT));
        // 创建 OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();
        // 创建请求体，携带 JSON 参数
        RequestBody requestBody = RequestBody.create(objectMapper.writeValueAsString(text),
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
            System.out.println(jsonNode.get("choices").get(0).get("message").get("content").asText());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
