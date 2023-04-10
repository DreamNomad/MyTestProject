package com.apiao;

import java.io.File;
/**
 * 文档转换
 * 例如：word、excel 转换为 pdf
 *
 * @author shanhy
 * @date 2020/12/17 11:27
 */
public interface JodConvertService {

    boolean convert(File sourceFile, File targetFile);

}