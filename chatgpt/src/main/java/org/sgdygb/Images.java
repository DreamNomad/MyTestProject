package org.sgdygb;

import java.io.Serializable;

/**
 * api请求的参数
 *
 * @author 提笔忘字的帝国
 */
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * prompt : A cute baby sea otter n : 2 size : 1024x1024
     */

    private String prompt;
    private int n;
    private String size;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
