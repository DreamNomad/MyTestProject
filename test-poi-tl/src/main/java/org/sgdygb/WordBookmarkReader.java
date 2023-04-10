package org.sgdygb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.w3c.dom.Node;

public class WordBookmarkReader {

    public static final String BOOKMARK_END_TAG = "w:bookmarkEnd";

    public static void main(String[] args) throws Exception {
        String inputFilePath = "D:/工艺策划模板.docx";
        File file = new File(inputFilePath);

        // 读取源文件
        FileInputStream fis = new FileInputStream(file);
        OPCPackage pkg = OPCPackage.open(fis);
        XWPFDocument document = new XWPFDocument(pkg);

        List<String> bookmarks = getAllBookmarks(document);

        System.out.println("Bookmarks in the document:");
        for (String bookmark : bookmarks) {
            System.out.println(bookmark);
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:/工艺策划模板11.docx");
        document.write(fileOutputStream);

        document.close();
        fileOutputStream.close();
    }

    private static List<String> getAllBookmarks(XWPFDocument document) {
        List<String> bookmarks = new ArrayList<>();

        for (IBodyElement element : document.getBodyElements()) {
            if (element instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph)element;
                bookmarks.addAll(getBookmarksInParagraph(paragraph));
            } else if (element instanceof XWPFTable) {
                XWPFTable table = (XWPFTable)element;
                bookmarks.addAll(getBookmarksInTable(table));
            }
        }

        List<XWPFHeader> headers = document.getHeaderList();
        for (XWPFHeader header : headers) {
            for (XWPFParagraph paragraph : header.getParagraphs()) {
                bookmarks.addAll(getBookmarksInParagraph(paragraph));
            }
            for (XWPFTable table : header.getTables()) {
                bookmarks.addAll(getBookmarksInTable(table));
            }
        }

        return bookmarks;
    }

    private static List<String> getBookmarksInParagraph(XWPFParagraph paragraph) {
        List<String> bookmarks = new ArrayList<>();
        CTP ctp = paragraph.getCTP();
        for (CTBookmark ctBookmark : ctp.getBookmarkStartList()) {
            bookmarks.add(ctBookmark.getName());

            if ("qqq".contains(ctBookmark.getName())) {

                XWPFRun run = paragraph.createRun();
                run.setText("测试插入");

                Node firstNode = ctBookmark.getDomNode();
                ctp.getDomNode().insertBefore(run.getCTR().getDomNode(), firstNode);
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
        return bookmarks;
    }

    private static List<String> getBookmarksInTable(XWPFTable table) {
        List<String> bookmarks = new ArrayList<>();

        for (XWPFTableRow row : table.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph paragraph : cell.getParagraphs()) {
                    bookmarks.addAll(getBookmarksInParagraph(paragraph));
                }
            }
        }

        return bookmarks;
    }
}
