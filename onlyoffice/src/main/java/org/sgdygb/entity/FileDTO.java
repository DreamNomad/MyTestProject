package org.sgdygb.entity;

import java.io.Serializable;

public class FileDTO implements Serializable {
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "FileDTO{" +
                "fileName='" + fileName + '\'' +
                '}';
    }
}
