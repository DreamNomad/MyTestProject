package org.sgdygb.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class IndexController {

    private static ThreadLocal<UUID> uuidThreadLocal = new ThreadLocal<UUID>() {
        @Override
        protected UUID initialValue() {
            return UUID.randomUUID();
        }
    };

    public static UUID getUUID() {
        return uuidThreadLocal.get();
    }

    public static Path getFile(String directory, String filename) throws IOException {
        Path dirPath = Paths.get(directory);
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);

        }
        //String[] names = {"jerry", "tom", "rose"};
        String[] names = new String[]{"jerry"};
        Path filePath = Paths.get(directory, new String[]{"jerry"});
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        return filePath;
    }

    public static void main(String[] args) {
        Path filePath = Paths.get("", new String[]{"jerry","11"});
        System.out.println(filePath.toString());
        //Path path = Paths.get("D:\\program4\\grid-platform-edition4.x\\appDatas\\npmn\\upload\\11\\1111.docx");
        //
        //// 如果文件不存在，则创建它
        //if (!Files.exists(path)) {
        //    try {
        //        System.out.println("新建");
        //        Files.createFile(path);
        //    } catch (IOException e) {
        //        e.printStackTrace();
        //    }
        //}
    }

    @RequestMapping("/test3")
    public void test3(HttpServletResponse response){
        try {
            Path path = Paths.get("D:\\program4\\grid-platform-edition4.x\\appDatas\\npmn\\upload\\test.docx");
            //Files.createDirectories(path.getParent());
            //Files.createFile(path);
            File file = path.toFile();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("test.docx", "utf-8"));
            response.setCharacterEncoding("utf-8");
            response.setContentLength((int) file.length());
            FileUtils.copyFile(file, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
