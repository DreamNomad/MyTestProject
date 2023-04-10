package org.sgdygb;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;

public class RepairExcel {

    public static void main(String[] args) throws IOException {
        repair("xlsx文件路径", "指定一个临时即可目录");
    }

    /**
     * 修复
     *
     * @param xlsxFilePath xlsx文件路径
     * @throws IOException ioexception
     */
    public static void repair(String xlsxFilePath, String tempDirPath) throws IOException {
        // 解压 zip 文件
        unzip(xlsxFilePath, tempDirPath);
        // 压缩文件
        addToZip(tempDirPath, xlsxFilePath);
        // 删除解压后的文件
        deleteFolder(new File(tempDirPath));
    }

    /**
     * 解压 zip 文件
     *
     * @param zipFilePath     压缩文件路径
     * @param destDirPath     解压后文件存放目录
     */
    private static void unzip(String zipFilePath, String destDirPath) {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                String filePath = destDirPath + File.separator + entry.getName();
                if (!entry.isDirectory()) {
                    // Check if the file name matches the filter condition
                    if (filePath.contains("docProps")) {
                        if (filePath.endsWith("app.xml") || filePath.endsWith("core.xml")){
                            extractFileFromZip(filePath, zipIn);
                        }
                    } else{
                        extractFileFromZip(filePath, zipIn);
                    }
                } else {
                    File dir = new File(filePath);
                    dir.mkdir();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        } catch (IOException e) {
            System.out.println("Failed to unzip file: " + e.getMessage());
        }
    }

    /**
     * 从zip提取文件
     *
     * @param filePath 文件路径
     * @param zipIn    ZipInputStream
     * @throws IOException ioexception
     */
    private static void extractFileFromZip(String filePath, ZipInputStream zipIn) throws IOException {
        byte[] buffer = new byte[1024];
        File parentDir = new File(filePath).getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            int len;
            while ((len = zipIn.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
        }
    }

    /**
     * 添加到zip
     *
     * @param sourceFilePath 源文件路径
     * @param destFilePath   压缩后文件存放目录
     * @throws IOException ioexception
     */
    private static void addToZip(String sourceFilePath, String destFilePath) throws IOException {
        // 1. 创建ZipArchiveOutputStream对象，指定压缩后的输出路径及文件名
        try(ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(new FileOutputStream(destFilePath))) {
            // 2. 获取需要压缩的文件列表
            List<File> fileList = (List<File>) FileUtils.listFiles(new File(sourceFilePath), null, true);

            // 3. 遍历文件列表，将文件压缩到ZipArchiveOutputStream中
            for (File file : fileList) {
                String fileName = file.getAbsolutePath().substring(sourceFilePath.length() + 1);
                ZipArchiveEntry entry = new ZipArchiveEntry(file, fileName);
                zipOut.putArchiveEntry(entry);
                FileInputStream fileIn = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fileIn.read(buffer)) != -1) {
                    zipOut.write(buffer, 0, len);
                }
                fileIn.close();
                zipOut.closeArchiveEntry();
            }
        }
    }

    /**
     * 删除文件夹
     *
     * @param folder 文件夹
     */
    private static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File file: files) {
                if(file.isDirectory()) {
                    deleteFolder(file);
                } else {
                    file.delete();
                }
            }
        }
        folder.delete();
    }

}
