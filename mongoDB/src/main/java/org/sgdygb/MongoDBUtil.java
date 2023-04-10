package org.sgdygb;

import java.util.Arrays;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MongoDBUtil {
    private static MongoClient mongoClient = null;

    /**
     * 获取MongoDB连接
     *
     * @param host MongoDB服务器地址
     * @param port MongoDB服务器端口
     * @param username MongoDB用户名
     * @param password MongoDB密码
     * @param poolSize 连接池大小
     * @return MongoClient实例
     */
    public static synchronized MongoClient getMongoClient(String host, int port, String username, String password,
        int poolSize) {
        if (mongoClient == null) {
            // 设置MongoDB连接池的大小
            MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(poolSize).build();

            // 设置MongoDB账户密码
            MongoCredential credential = MongoCredential.createCredential(username, "admin", password.toCharArray());

            // 创建MongoClient实例
            mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential), options);
        }
        return mongoClient;
    }
}
