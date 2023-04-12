package org.sgdygb.entity;

/**
 * 转换
 *
 * @author DengLS
 * @date 2023/04/10
 */
public class Conversion {

    /**
     * async : false filetype : docx key : Khirz6zTPdfd7 outputtype : pdf title : Example Document Title.docx url :
     * https://example.com/url-to-example-document.docx
     */

    private boolean async;
    private String filetype;
    private String key;
    private String outputtype;
    private String title;
    private String url;

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOutputtype() {
        return outputtype;
    }

    public void setOutputtype(String outputtype) {
        this.outputtype = outputtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
