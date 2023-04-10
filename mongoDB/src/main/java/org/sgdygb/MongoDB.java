package org.sgdygb;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * MongoDB
 */
public class MongoDB {

    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;

    /**
     * 私有构造函数，初始化MongoDB连接
     */
    private MongoDB() {
        try {
            // 两种方式实例化
            // 第一种
            // MongoCredential credential = MongoCredential.createCredential(username, database,
            // password.toCharArray());
            // MongoClientSettings settings = MongoClientSettings.builder().credential(credential)
            // .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(host, port)))).build();
            // MongoClient mongoClient = MongoClients.create(settings);
            // MongoDatabase db = mongoClient.getDatabase(database);

            // 第二种
            // 连接MongoDB
            MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://root:root@localhost:27017"))
                .applyToConnectionPoolSettings(builder -> builder.maxSize(10).minSize(5))
                .writeConcern(WriteConcern.MAJORITY).build();

            mongoClient = MongoClients.create(settings);
            // mongoClient = MongoClients.create("mongodb://root:root@localhost:27017");
            // 获取数据库
            mongoDatabase = mongoClient.getDatabase("test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class MongoDBHolder {
        private static final MongoDB INSTANCE = new MongoDB();
    }

    /**
     * 获取MongoDB实例
     *
     * @return MongoDB实例
     */
    public static synchronized MongoDB getInstance() {
        return MongoDBHolder.INSTANCE;
    }

    /**
     * 获取MongoDB数据库
     *
     * @return MongoDB数据库
     */
    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    /**
     * 关闭MongoDB连接
     */
    public void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

}
