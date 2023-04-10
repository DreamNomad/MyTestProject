package org.sgdygb;

import java.util.List;

public class Entity {
    /**
     * tableIndex : 0 data : [{"rowIndex":0,"colIndex":0,"content":"测试"}]
     */

    private int tableIndex;
    private List<DataBean> data;

    public int getTableIndex() {
        return tableIndex;
    }

    public void setTableIndex(int tableIndex) {
        this.tableIndex = tableIndex;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * rowIndex : 0 colIndex : 0 content : 测试
         */

        private int rowIndex;
        private int colIndex;
        private String content;

        public int getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public int getColIndex() {
            return colIndex;
        }

        public void setColIndex(int colIndex) {
            this.colIndex = colIndex;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    // 根据单元格下标插入文本实体

}
