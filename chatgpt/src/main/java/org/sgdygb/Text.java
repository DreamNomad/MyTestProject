package org.sgdygb;

import java.io.Serializable;
import java.util.List;

/**
 * api请求的参数
 *
 * @author 提笔忘字的帝国
 */
public class Text implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * model : gpt-3.5-turbo messages : [{"role":"user","content":"Say this is a test!"}] temperature : 0.7
     */

    private String model;
    private double temperature;
    private List<MessagesBean> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public List<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class MessagesBean implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * role : user content : Say this is a test!
         */

        private String role;
        private String content;

        public MessagesBean(String user, String content) {
            this.role = user;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
