//package com.apiao;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFPictureData;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.util.CellRangeAddress;
//
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.Rectangle;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
///**
// * Excel2003文件转化为PDF文件
// *
// * @author zyj
// * @date 2019-10-18
// */
//public class Xls2Pdf {
//    private HSSFWorkbook _wb = null;
//    private int _sheetIndex = 0;
//    private HSSFSheet _sheet = null;
//    // 文本中的合并单元格
//    private List<CellRangeAddress> _ranges = null;
//    // 单个Sheet中所有的图片信息
//    private Map<String, HSSFPictureData> _pics = null;
//    // 图片中的最后一个单元格位置
//    private Integer[] _lastCell = null;
//
//    /**
//     * 构造函数
//     *
//     * @param excel      excel2003文件的全路径
//     * @param sheetindex sheet索引
//     * @throws IOException
//     */
//    public Xls2Pdf(String excel, int sheetIndex) throws IOException {
//        InputStream input = new FileInputStream(excel);
//        HSSFWorkbook wb = new HSSFWorkbook(input);
//        this._wb = wb;
//        this._sheetIndex = sheetIndex;
//    }
//
//    /**
//     * 构造函数
//     *
//     * @param wb         excel工作薄
//     * @param sheetIndex sheet索引
//     */
//    public Xls2Pdf(HSSFWorkbook wb, int sheetIndex) {
//        this._wb = wb;
//        this._sheetIndex = sheetIndex;
//    }
//
//    /**
//     * 保存为pdf文件
//     *
//     * @param pdf pdf文件的全路径
//     * @throws IOException
//     * @throws BadElementException
//     * @throws DocumentException
//     */
//    public void savePdf(String pdf) throws IOException, BadElementException, DocumentException {
//        PdfPTable table = this.genPdfPTable();
//        // Step 1 — Create a Document
//        Document document = new Document();
//        // Step 2 — Get a PdfWriter instance
//        FileOutputStream output = new FileOutputStream(pdf);
//        PdfWriter.getInstance(document, output);
//        // Step 3 — Open the Document
//        document.open();
//        // Step 4 — Add content.
//        document.add(table);
//        // Step 5 — Close the Document
//        document.close();
//    }
//
//    /**
//     * 将sheet转化为PdfPTable
//     *
//     * @return PdfPTable对象
//     * @throws IOException
//     * @throws BadElementException
//     * @throws DocumentException
//     */
//    public PdfPTable genPdfPTable() throws IOException, BadElementException, DocumentException {
//        if (this._wb == null) {
//            return null;
//        }
//        this._sheet = this._wb.getSheetAt(this._sheetIndex);
//        if (this._sheet == null) {
//            return null;
//        }
//        // 文本中的合并单元格
//        this._ranges = this._sheet.getMergedRegions();
//        // sheet中所有图片信息
//        this._pics = XlsHelper.getPictrues(this._wb, this._sheetIndex);
//        // sheet中最后一个单元格位置
//        this._lastCell = this.getLastCell();
//        PdfPTable table = new PdfPTable(this._lastCell[1] + 1);
//        // 遍历Sheet中的所有行
//        for (int i = 0; i <= this._lastCell[0]; i++) {
//            for (int j = 0; j <= this._lastCell[1]; j++) {
//                PdfPCell pdfpCell = this.genPdfPCell(i, j);
//                if (pdfpCell == null) {
//                    continue;
//                }
//                table.addCell(pdfpCell);
//                j += pdfpCell.getColspan() - 1;
//            }
//        }
//        // sheet中每列的宽度
//        float totalWidth = 0;
//        float[] widths = new float[this._lastCell[1] + 1];
//        for (int i = 0; i <= this._lastCell[1]; i++) {
//            widths[i] = this._sheet.getColumnWidthInPixels(i);
//            totalWidth += widths[i];
//        }
//        // PDF宽度固定
//        table.setWidths(widths);
//        table.setTotalWidth(totalWidth);
//        table.setLockedWidth(true);
//        return table;
//    }
//
//    /**
//     * 将Exel单元格转化为PDF单元格
//     *
//     * @param rowIndex 行号
//     * @param colIndex 列号
//     * @return PDF单元格
//     * @throws IOException
//     * @throws BadElementException
//     */
//    private PdfPCell genPdfPCell(int rowIndex, int colIndex) throws IOException, BadElementException {
//        // 如果该位置是合并区域的单元格，跳过。
//        if (this.merged(rowIndex, colIndex)) {
//            return null;
//        }
//        // 是否为图片的起始单元格
//        boolean isImg = this.imgCell(rowIndex, colIndex);
//        PdfPCell pdfpCell = null;
//        HSSFRow row = this._sheet.getRow(rowIndex);
//        if (row == null) {
//            // 空单元格，无边框
//            pdfpCell = new PdfPCell();
//            pdfpCell.setBorder(Rectangle.NO_BORDER);
//        } else {
//            HSSFCell cell = row.getCell(colIndex);
//            // 空单元格的处理
//            if (cell == null) {
//                cell = row.createCell(colIndex);
//            }
//            pdfpCell = Xls2PdfObject.parsePdfPCell(this._wb, cell);
//            if (!isImg) {
//                // 非图片，填充文本内容
//                Phrase phrase = Xls2PdfObject.parsePhrase(this._wb, cell);
//                pdfpCell.setPhrase(phrase);
//            }
//        }
//        if (isImg) {
//            // 设置图片信息，图片优先，可能占用多个单元格（图片涉及的单元格无文本内容，且只会有一张图片）
//            this.setImage(pdfpCell, rowIndex, colIndex);
//        } else {
//            // 设置文本的合并单元格
//            this.setRange(pdfpCell, rowIndex, colIndex);
//        }
//        // 设置单元格的高度
//        float height = this.getHeight(rowIndex, pdfpCell.getRowspan());
//        pdfpCell.setMinimumHeight(height);
//        return pdfpCell;
//    }
//
//    /**
//     * 设置文本的合并单元格
//     *
//     * @param pdfpCell
//     * @param rowIndex
//     * @param colIndex
//     */
//    private void setRange(PdfPCell pdfpCell, int rowIndex, int colIndex) {
//        CellRangeAddress range = this.getCellRangeAddress(rowIndex, colIndex);
//        int rowspan = 1;
//        int colspan = 1;
//        if (range != null) {
//            rowspan = range.getLastRow() - range.getFirstRow() + 1;
//            colspan = range.getLastColumn() - range.getFirstColumn() + 1;
//        }
//        pdfpCell.setRowspan(rowspan);
//        pdfpCell.setColspan(colspan);
//    }
//
//    /**
//     * 设置图片信息，图片优先，可能占用多个单元格（图片涉及的单元格无文本内容，且只会有一张图片）
//     *
//     * @param pdfpCell
//     * @param rowIndex
//     * @param colIndex
//     * @throws IOException
//     * @throws BadElementException
//     */
//    private void setImage(PdfPCell pdfpCell, int rowIndex, int colIndex) throws IOException, BadElementException {
//        for (Map.Entry<String, HSSFPictureData> entry : this._pics.entrySet()) {
//            int[] arr = XlsHelper.getImgPostion(entry.getKey());
//            if (arr[1] == rowIndex && arr[2] == colIndex) {
//                int rowspan = arr[3] - arr[1] + 1;
//                int colspan = arr[4] - arr[2] + 1;
//                pdfpCell.setRowspan(rowspan);
//                pdfpCell.setColspan(colspan);
//                // 内边距
//                pdfpCell.setPaddingLeft(arr[5] / 20.00f);
//                pdfpCell.setPaddingTop(arr[6] / 32.00f);
//                pdfpCell.setPaddingRight(arr[7] / 20.00f);
//                pdfpCell.setPaddingBottom(arr[8] / 32.00f);
//                byte[] bytes = entry.getValue().getData();
//                Image image = Image.getInstance(bytes);
//                pdfpCell.setImage(image);
//                break;
//            }
//        }
//    }
//
//    /**
//     * 单元格高度
//     *
//     * @param rowindex 单元格起始行号
//     * @param rowspan  单元格占用行数
//     * @return 高度，单位pt
//     */
//    private float getHeight(int rowIndex, int rowSpan) {
//        float height = 0.00f;
//        for (int i = 0; i < rowSpan; i++) {
//            HSSFRow row = this._sheet.getRow(rowIndex + i);
//            if (row == null) {
//                height += this._sheet.getDefaultRowHeightInPoints();
//            } else {
//                height += row.getHeightInPoints();
//            }
//        }
//        return height;
//    }
//
//    /**
//     * 合并区域的单元格（不包括初始单元格）
//     *
//     * @param rowIndex 行号
//     * @param colIndex 列号
//     * @return false表示该位置不是合并单元格，或者是合并区域的起始单元格
//     */
//    private boolean merged(int rowIndex, int colIndex) {
//        boolean isMerge = false;
//        // 文本合并区域的单元格
//        if (this._ranges != null && this._ranges.size() > 0) {
//            int num = this._ranges.size();
//            for (int i = 0; i < num; i++) {
//                CellRangeAddress range = this._ranges.get(i);
//                if (rowIndex > range.getFirstRow() && rowIndex <= range.getLastRow()) {
//                    if (colIndex >= range.getFirstColumn() && colIndex <= range.getLastColumn()) {
//                        isMerge = true;
//                    }
//                }
//            }
//        }
//        // 图片合并区域的单元格
//        if (this._pics != null && this._pics.size() > 0) {
//            for (Map.Entry<String, HSSFPictureData> entry : this._pics.entrySet()) {
//                int[] arr = XlsHelper.getImgPostion(entry.getKey());
//                if (rowIndex > arr[1] && rowIndex <= arr[3]) {
//                    if (colIndex >= arr[2] && colIndex <= arr[4]) {
//                        isMerge = true;
//                    }
//                }
//            }
//        }
//        return isMerge;
//    }
//
//    /**
//     * 单元格是否是图片的起始位置
//     *
//     * @param rowIndex 行号
//     * @param colIndex 列号
//     * @return 单元格是否是图片的起始位置
//     */
//    private boolean imgCell(int rowIndex, int colIndex) {
//        if (this._pics == null || this._pics.size() == 0) {
//            return false;
//        }
//        for (Map.Entry<String, HSSFPictureData> entry : this._pics.entrySet()) {
//            int[] arr = XlsHelper.getImgPostion(entry.getKey());
//            if (arr[1] == rowIndex && arr[2] == colIndex) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 如果单元格是文本合并区域的起始位置，返回合并区域；否则返回null
//     *
//     * @param rowIndex 行号
//     * @param colIndex 列号
//     * @return 合并区域
//     */
//    private CellRangeAddress getCellRangeAddress(int rowIndex, int colIndex) {
//        if (this._ranges == null || this._ranges.size() == 0) {
//            return null;
//        }
//        int num = this._ranges.size();
//        for (int i = 0; i < num; i++) {
//            CellRangeAddress range = this._ranges.get(i);
//            if (range.getFirstColumn() == colIndex && range.getFirstRow() == rowIndex) {
//                return range;
//            }
//        }
//        return null;
//    }
//
//    /**
//     * sheet中最后一个单元格位置
//     *
//     * @return 行号，列号
//     */
//    private Integer[] getLastCell() {
//        // 文本中最后一个单元格的位置
//        Integer[] lastTextCell = this.getLastTextCell();
//        // 图片中的最后一个单元格位置
//        Integer[] lastImgCell = this.getLastImgCell();
//        if (lastImgCell == null || lastImgCell.length == 0) {
//            return lastTextCell;
//        }
//        Integer[] lastCell = new Integer[] { 0, 0 };
//        lastCell[0] = lastTextCell[0] >= lastImgCell[0] ? lastTextCell[0] : lastImgCell[0];
//        lastCell[1] = lastTextCell[1] >= lastImgCell[1] ? lastTextCell[1] : lastImgCell[1];
//        return lastCell;
//    }
//
//    /**
//     * sheet中最后一个文本单元格
//     *
//     * @return 行号，列号
//     */
//    private Integer[] getLastTextCell() {
//        int lastRowIndex = this._sheet.getLastRowNum();
//        int lastColIndex = 0;
//        for (int i = 0; i <= lastRowIndex; i++) {
//            HSSFRow row = this._sheet.getRow(i);
//            if (row == null) {
//                continue;
//            }
//            // 该行最后一个单元格的索引
//            int colIndex = row.getLastCellNum();
//            if (colIndex > lastColIndex) {
//                lastColIndex = colIndex;
//            }
//        }
//        return new Integer[] { lastRowIndex, lastColIndex - 1 };
//    }
//
//    /**
//     * sheet的最后一个图片单元格
//     *
//     * @return 行号，列号
//     */
//    private Integer[] getLastImgCell() {
//        if (this._pics == null || this._pics.size() == 0) {
//            return null;
//        }
//        int lastRowIndex = 0;
//        int lastColIndex = 0;
//        for (Map.Entry<String, HSSFPictureData> entry : this._pics.entrySet()) {
//            int[] arr = XlsHelper.getImgPostion(entry.getKey());
//            if (arr[0] != this._sheetIndex) {
//                continue;
//            }
//            if (arr[3] > lastRowIndex) {
//                lastRowIndex = arr[3];
//            }
//            if (arr[4] > lastColIndex) {
//                lastColIndex = arr[4];
//            }
//        }
//        return new Integer[] { lastRowIndex, lastColIndex };
//    }
//
//}