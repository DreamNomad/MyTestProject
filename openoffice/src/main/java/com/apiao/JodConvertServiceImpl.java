package com.apiao;

import lombok.extern.slf4j.Slf4j;
import org.jodconverter.core.DocumentConverter;
import org.jodconverter.core.office.OfficeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Office转换为PDF
 *
 * @author shanhy
 * @date 2020/12/16 16:58
 */
@Service
@Slf4j
public class JodConvertServiceImpl implements JodConvertService {

    /**
     * 转换器注入
     */
    @Autowired
    private DocumentConverter converter;

    @Override
    public boolean convert(File sourceFile, File targetFile) {
        try {
            converter.convert(sourceFile).to(targetFile).execute();
            return true;
        } catch (OfficeException e) {
            log.error("转换office文档失败", e);
        }
        return false;
    }

}