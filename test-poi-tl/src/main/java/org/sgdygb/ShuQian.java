package org.sgdygb;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.w3c.dom.Node;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ConfigureBuilder;

/**
 * 书签替换（word）
 *
 * @author Administrator
 * @date 2023/04/01
 */
public class ShuQian {
    public static final String RUN_NODE_NAME = "w:r";
    public static final String TEXT_NODE_NAME = "w:t";
    public static final String BOOKMARK_START_TAG = "w:bookmarkStart";
    public static final String BOOKMARK_END_TAG = "w:bookmarkEnd";
    public static final String BOOKMARK_ID_ATTR_NAME = "w:id";
    public static final String STYLE_NODE_NAME = "w:rPr";

    public static void main(String[] args) {

        ConfigureBuilder builder = Configure.builder();
        builder.setValidErrorHandler(new Configure.DiscardHandler());

        try {
            FileInputStream is = new FileInputStream("D:/test01.docx");
            // XWPFDocument doc = new XWPFDocument(is);

            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("test", "大白222");
            // refreshBooks(doc, dataMap);
            docxOperate(is, new FileOutputStream("D:/test02.docx"), dataMap);
            // doc.write(new FileOutputStream("D:/test01.docx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void docxOperate(InputStream inputStream, OutputStream outputStream, Map<String, String> dataMap)
        throws IOException {
        XWPFDocument document = new XWPFDocument(inputStream).getXWPFDocument();
        List<XWPFParagraph> paragraphList = document.getParagraphs();
        for (XWPFParagraph xwpfParagraph : paragraphList) {
            CTP ctp = xwpfParagraph.getCTP();

            for (int dwI = 0; dwI < ctp.sizeOfBookmarkStartArray(); dwI++) {
                CTBookmark bookmark = ctp.getBookmarkStartArray(dwI);
                if (dataMap.containsKey(bookmark.getName())) {

                    XWPFRun run = xwpfParagraph.createRun();
                    run.setText(dataMap.get(bookmark.getName()));

                    Node firstNode = bookmark.getDomNode();
                    Node nextNode = firstNode.getNextSibling();
                    while (nextNode != null) {
                        String nodeName = nextNode.getNodeName();
                        if (nodeName.equals(BOOKMARK_END_TAG)) {
                            break;
                        }

                        Node delNode = nextNode;
                        nextNode = nextNode.getNextSibling();

                        ctp.getDomNode().removeChild(delNode);
                    }

                    if (nextNode == null) {
                        ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), firstNode);
                    } else {
                        ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), nextNode);
                    }
                }
            }
        }

        document.write(outputStream);
        document.close();
    }
}
