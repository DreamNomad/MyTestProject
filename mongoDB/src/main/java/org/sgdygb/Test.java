package org.sgdygb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Test {

    private static MongoDB mongoDB;

    public static void main(String[] args) {
        mongoDB = MongoDB.getInstance();
        MongoDatabase database = mongoDB.getMongoDatabase();
        MongoCollection<Document> collection = database.getCollection("jdbc_log");
        findLog(collection);
    }

    private static void findLog(MongoCollection<Document> collection) {
        // 查询MongoDB中的日志
        try (MongoCursor<Document> cursor = collection.find().iterator()) {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        }
    }

    private static void insertLog(MongoCollection<Document> collection) {
        // JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            String sql = "insert into tt(id, xh) values (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, (int)System.currentTimeMillis());
            ps.setString(2, "测试日志插入");
            ps.executeUpdate();

            // 获取JDBC操作日志
            String jdbcLog = ps.toString();
            // 将JDBC操作日志存储到MongoDB中
            Document document = new Document();
            document.append("time", new Date());
            document.append("operation", "insert");
            document.append("sql", jdbcLog);
            collection.insertOne(document);
            System.out.println(document.get("_id"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
                mongoDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
