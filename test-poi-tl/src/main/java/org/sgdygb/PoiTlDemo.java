package org.sgdygb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.Texts;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;

public class PoiTlDemo {
    public static void main(String[] args) throws IOException {

        // ArrayList<String> list = new ArrayList<String>();
        // list.add("111");
        // list.add("111");
        // list.add("111");
        // list.add("111");
        // for (int i = 0; i < list.size(); i++) {
        //
        // }

        // FileInputStream fis = new FileInputStream("D:\\工艺策划的模板.docx");
        // XWPFDocument doc = new XWPFDocument(fis);
        // fis.close();
        //
        //// 选择要插入文本的表格
        // XWPFTable table = doc.getTables().get(0);
        //
        //
        //
        //
        //// 选择要插入文本的单元格
        // table.getRow(0).getCell(1).setText("测试");
        //
        //// 将更新后的文档写入新文件
        // doc.write(new FileOutputStream("D:\\11111output.docx"));
        // doc.close();

        List<Map<String, Object>> objects = new ArrayList<>();

        Map<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("test1", "1111");
        stringObjectHashMap.put("test2", "222");
        stringObjectHashMap.put("test3", "3333");
        stringObjectHashMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> {
            Object value = entry.getValue();

            if ((value instanceof String) && StringUtils.isNotBlank(value.toString())) {
                return Texts.of(value.toString()).color("000000").create();
            } else {
                return value;
            }
        }));

        for (int i = 0; i < 50; i++) {
            objects.add(stringObjectHashMap);
        }

        // objects.add(stringObjectHashMap);

        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        // builder.setValidErrorHandler(new Configure.DiscardHandler());
        Configure config =
            Configure.builder().bind("table", policy).setValidErrorHandler(new Configure.DiscardHandler()).build();

        XWPFTemplate template = XWPFTemplate.compile("D:\\111.docx", config).render(new HashMap<String, Object>() {
            {
                put("projectname", Texts.of("不能编辑").color("000000").create());
                put("table", objects);
            }
        });
        template.writeAndClose(new FileOutputStream("D:\\11111output.docx"));
    }
}
