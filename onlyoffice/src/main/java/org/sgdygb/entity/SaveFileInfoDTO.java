package org.sgdygb.entity;

import java.util.List;

/**
 * 保存文件信息dto
 *
 * @author Administrator
 * @date 2023/03/15
 */
public class SaveFileInfoDTO {

    /**
     * lastsave : 2023-03-15T06:40:15.000Z
     * notmodified : true
     * changesurl : http://192.168.192.100:9999/cache/files/3232_3937/changes.zip/changes.zip?md5=6HWuhP3by8cdEKkrt2J_2g&expires=1678863409&disposition=attachment&filename=changes.zip
     * history : {"serverVersion":"6.3.1","changes":[{"created":"2023-03-15 06:38:39","user":{"name":"一个人","id":"111"}},{"created":"2023-03-15 06:40:15","user":{"name":"11","id":"222"}}]}
     * forcesavetype : 1
     * actions : [{"type":0,"userid":"222"}]
     * key : 3232
     * url : http://192.168.192.100:9999/cache/files/3232_3937/output.docx/output.docx?md5=GXn2sO9IfwZhZa0QwN06Sg&expires=1678863409&disposition=attachment&filename=output.docx
     * users : ["222"]
     * status : 2
     */

    private String lastsave;
    private boolean notmodified;
    private String changesurl;
    private HistoryBean history;
    private int forcesavetype;
    private String key;
    private String url;
    private int status;
    private List<ActionsBean> actions;
    private List<String> users;

    public String getLastsave() {
        return lastsave;
    }

    public void setLastsave(String lastsave) {
        this.lastsave = lastsave;
    }

    public boolean isNotmodified() {
        return notmodified;
    }

    public void setNotmodified(boolean notmodified) {
        this.notmodified = notmodified;
    }

    public String getChangesurl() {
        return changesurl;
    }

    public void setChangesurl(String changesurl) {
        this.changesurl = changesurl;
    }

    public HistoryBean getHistory() {
        return history;
    }

    public void setHistory(HistoryBean history) {
        this.history = history;
    }

    public int getForcesavetype() {
        return forcesavetype;
    }

    public void setForcesavetype(int forcesavetype) {
        this.forcesavetype = forcesavetype;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ActionsBean> getActions() {
        return actions;
    }

    public void setActions(List<ActionsBean> actions) {
        this.actions = actions;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public static class HistoryBean {
        /**
         * serverVersion : 6.3.1
         * changes : [{"created":"2023-03-15 06:38:39","user":{"name":"一个人","id":"111"}},{"created":"2023-03-15 06:40:15","user":{"name":"11","id":"222"}}]
         */

        private String serverVersion;
        private List<ChangesBean> changes;

        public String getServerVersion() {
            return serverVersion;
        }

        public void setServerVersion(String serverVersion) {
            this.serverVersion = serverVersion;
        }

        public List<ChangesBean> getChanges() {
            return changes;
        }

        public void setChanges(List<ChangesBean> changes) {
            this.changes = changes;
        }

        public static class ChangesBean {
            /**
             * created : 2023-03-15 06:38:39
             * user : {"name":"一个人","id":"111"}
             */

            private String created;
            private UserBean user;

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                /**
                 * name : 一个人
                 * id : 111
                 */

                private String name;
                private String id;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }
            }
        }
    }

    public static class ActionsBean {
        /**
         * type : 0
         * userid : 222
         */

        private int type;
        private String userid;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
