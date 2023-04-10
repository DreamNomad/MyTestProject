package com.apiao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author shanhy
 * @date 2020/12/16 17:14
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private JodConvertService officeConvertService;

    @GetMapping("/test1")
    public boolean test1(String sourceFileName, String targetFileName) {
        File sourceFile = new File("/root/".concat(sourceFileName));
        File targetFile = new File("/root/".concat(targetFileName));
        return officeConvertService.convert(sourceFile, targetFile);
    }
}