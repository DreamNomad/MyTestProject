package org.sgdygb.controller;

import ch.qos.logback.core.util.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ClassUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.ExceptionUtils;
import org.sgdygb.entity.FileDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/onlyoffice")
public class OnlyOfficeController {

    public static void main(String[] args) {

        System.out.println(Paths.get("test\\tet\\", "\\t11.txt").toString());
        //ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.
        //System.out.println(objectMapper.toString());
        //
        //UUID uuid = UUID.nameUUIDFromBytes(("测试\\测试文件.doc").getBytes());
        //System.out.println(uuid.toString());
        //System.out.println(UUID.randomUUID());
        //try {
        //    String orCreateFile = findOrCreateFile("D:\\testtetet", "测试", "docx");
        //    System.out.println(orCreateFile);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
    }



    public static String findOrCreateFile(String directory, String fileName, String extension) throws IOException {
        Path path = Paths.get(directory, fileName + "." + extension);
        System.out.println(path.toString());
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block

        }
        return path.toAbsolutePath().toString();
    }
    /**
     * 0 - 找不到具有密钥标识符的文档
     * 1 - 正在编辑文档
     * 2 - 文档已准备好保存
     * 3 - 发生文档保存错误
     * 4 - 不作任何更改就关闭文档
     * 6 - 正在编辑文档，但保存当前文档状态
     * 7 - 强制保存文档时发生错误
     */
    @PostMapping("/save")
    public String save(@RequestParam Map<String, String> map, HttpServletRequest request, HttpServletResponse response){
        //PrintWriter writer = null;
        try {
            //writer = response.getWriter();
            // 获取传输的json数据
            Scanner scanner = new Scanner(request.getInputStream()).useDelimiter("\\A");
            String body = scanner.hasNext() ? scanner.next() : "";
            JSONObject jsonObject = JSONObject.parseObject(body);
            String fileName = map.get("fileName");
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            System.out.println(jsonObject);
            String url = jsonObject.getString("url");

            int status = jsonObject.getIntValue("status");
            //String key = jsonObject.getString("key");

            if (2 == status || 3 == status || 6 == status || 7 == status) {
                FileUtils.copyURLToFile(new URL(url),new File(path.concat("static/").concat(fileName)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{\"error\":0}";
    }

}
