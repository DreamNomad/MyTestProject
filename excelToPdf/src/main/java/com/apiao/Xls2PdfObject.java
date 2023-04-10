//package com.apiao;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.format.CellNumberFormatter;
//import org.apache.poi.ss.usermodel.BorderStyle;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.FontUnderline;
//import org.apache.poi.ss.usermodel.HorizontalAlignment;
//import org.apache.poi.ss.usermodel.VerticalAlignment;
//
//import com.itextpdf.text.Anchor;
//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//
///**
// * Excel2003中的poi对象转化为itext对象
// *
// * @author zyj
// * @date 2019-10-23
// */
//public class Xls2PdfObject {
//    /**
//     * HSSFCell转化为PdfPCell
//     *
//     * @param wb
//     * @param cell
//     * @return
//     */
//    public static PdfPCell parsePdfPCell(HSSFWorkbook wb, HSSFCell cell) {
//        PdfPCell pdfpCell = new PdfPCell();
//        HSSFCellStyle cellStyle = cell.getCellStyle();
//        pdfpCell.setUseAscender(true);
//        // 水平对齐方式
//        HorizontalAlignment halign = cellStyle.getAlignment();
//        int halign_itext = parseHorizontalAlignmen(halign);
//        pdfpCell.setHorizontalAlignment(halign_itext);
//        // 垂直对齐方式
//        VerticalAlignment valign = cellStyle.getVerticalAlignment();
//        int valign_itext = parseVerticalAlignment(valign);
//        pdfpCell.setVerticalAlignment(valign_itext);
//        // 填充色（背景色）
//        // HSSFColor backgroundColor = cellStyle.getFillBackgroundColorColor();
//        HSSFColor backgroundColor = cellStyle.getFillForegroundColorColor();
//        BaseColor backgroundColor_itext = parseBackgroundColor(backgroundColor);
//        pdfpCell.setBackgroundColor(backgroundColor_itext);
//        // 自动换行
//        boolean noWrap = !cellStyle.getWrapText();
//        pdfpCell.setNoWrap(noWrap);
//
//        // 边框颜色设置
//        // 下框线
//        short borderColor = cellStyle.getBottomBorderColor();
//        HSSFColor color = wb.getCustomPalette().getColor(borderColor);
//        BaseColor baseColor = parseColor(color);
//        pdfpCell.setBorderColorBottom(baseColor);
//        // 上框线
//        borderColor = cellStyle.getTopBorderColor();
//        baseColor = parseColor(color);
//        pdfpCell.setBorderColorTop(baseColor);
//        // 左框线
//        borderColor = cellStyle.getLeftBorderColor();
//        baseColor = parseColor(color);
//        pdfpCell.setBorderColorLeft(baseColor);
//        // 右框线
//        borderColor = cellStyle.getRightBorderColor();
//        baseColor = parseColor(color);
//        pdfpCell.setBorderColorRight(baseColor);
//
//        // 边框样式
//        // 下边框
//        BorderStyle borderStyle = cellStyle.getBorderBottom();
//        float borderWidth = borderStyle.getCode() / 32.00f;
//        pdfpCell.setBorderWidthBottom(borderWidth);
//        // 上框线
//        borderStyle = cellStyle.getBorderTop();
//        pdfpCell.setBorderWidthTop(borderStyle.getCode() / 32.00f);
//        // 左框线
//        borderStyle = cellStyle.getBorderLeft();
//        pdfpCell.setBorderWidthLeft(borderStyle.getCode() / 32.00f);
//        // 右框线
//        borderStyle = cellStyle.getBorderRight();
//        pdfpCell.setBorderWidthRight(borderStyle.getCode() / 32.00f);
//
//        pdfpCell.normalize();
//        // pdfpCell.disableBorderSide(9);
//        return pdfpCell;
//    }
//
//    /**
//     * HSSFCell转化为Phrase
//     *
//     * @param wb
//     * @param cell
//     * @return
//     */
//    public static Phrase parsePhrase(HSSFWorkbook wb, HSSFCell cell) {
//        HSSFCellStyle cellStyle = cell.getCellStyle();
//        double cellNumberValue = 0;
//        boolean isNumber = cell.getCellType() == CellType.NUMERIC;
//        if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
//            try {
//                cellNumberValue = cell.getNumericCellValue();
//                isNumber = true;
//            } catch (Exception e) {
//                isNumber = false;
//            }
//        }
//        int index = cellStyle.getFontIndexAsInt();
//        HSSFFont xlsFont = wb.getFontAt(index);
//        Font itextFont = parseFont(xlsFont);
//        String formatStr = cellStyle.getDataFormatString();
//        if (!"general".equals(formatStr.toLowerCase()) && isNumber) {
//            String numberFormat = formatStr;
//            int firstFormatIdx = formatStr.indexOf(";");
//            if (firstFormatIdx > 0) {
//                numberFormat = formatStr.substring(0, firstFormatIdx);
//            }
//            String formattedValue = new CellNumberFormatter(numberFormat).format(cellNumberValue);
//            Phrase phrase = new Phrase(formattedValue, itextFont);
//            return phrase;
//        }
//        cell.setCellType(CellType.STRING);
//        String text = cell.getStringCellValue();
//        Anchor anchor = new Anchor(text, itextFont);
//        anchor.setName(text);
//        return anchor;
//    }
//
//    /**
//     * HSSFFont转化为itext的Font
//     *
//     * @param xlsFont HSSFFont
//     * @return Font
//     */
//    public static Font parseFont(HSSFFont xlsFont) {
//        // 字体名称，编码方式，，字体大小，字体样式，字体颜色
//        String fontName = xlsFont.getFontName();
//        // 字体编码
//        String encoding = "UniGB-UCS2-H";
//        // 未嵌入PDF字体
//        boolean embedded = BaseFont.EMBEDDED;
//        // 字体大小
//        float size = xlsFont.getFontHeightInPoints();
//
//        // Font itextFont = FontFactory.getFont(fontName, encoding, embedded, size);
//        Font itextFont = FontFactory.getFont("STSong-Light", encoding, embedded, size);
//
//        // 字体颜色
//        int colorIndex = xlsFont.getColor();
//        HSSFColor color = HSSFColor.getIndexHash().get(colorIndex);
//        BaseColor baseColor = parseColor(color);
//        itextFont.setColor(baseColor);
//
//        // 加粗、倾斜
//        boolean isItalic = xlsFont.getItalic();
//        boolean isBold = xlsFont.getBold();
//        if (isItalic && isBold) {
//            itextFont.setStyle(Font.BOLDITALIC);
//        } else if (isBold) {
//            itextFont.setStyle(Font.BOLD);
//        } else if (isItalic) {
//            itextFont.setStyle(Font.ITALIC);
//        }
//        // 下划线
//        FontUnderline underline = FontUnderline.valueOf(xlsFont.getUnderline());
//        if (underline != FontUnderline.NONE) {
//            itextFont.setStyle(Font.UNDERLINE);
//        }
//        return itextFont;
//    }
//
//    /**
//     * 水平对齐方式转化 - poi转化为itext
//     *
//     * @param halign poi的水平对齐方式
//     * @return itext的水平对齐方式
//     */
//    public static int parseHorizontalAlignmen(HorizontalAlignment halign) {
//        int halign_itext = Element.ALIGN_LEFT;
//        // 获取单元格的水平对齐方式
//        switch (halign) {
//            case LEFT:
//                halign_itext = Element.ALIGN_LEFT;
//                break;
//            case CENTER:
//                halign_itext = Element.ALIGN_CENTER;
//                break;
//            case RIGHT:
//                halign_itext = Element.ALIGN_RIGHT;
//                break;
//        }
//        return halign_itext;
//    }
//
//    /**
//     * 垂直对齐方式 转化- poi转化为itext:
//     *
//     * @param valign poi的垂直对齐方式
//     * @return itext的垂直对齐方式
//     */
//    public static int parseVerticalAlignment(VerticalAlignment valign) {
//        int valign_itext = Element.ALIGN_BOTTOM;
//        switch (valign) {
//            case TOP:
//                valign_itext = Element.ALIGN_TOP;
//                break;
//            case CENTER:
//                valign_itext = Element.ALIGN_MIDDLE;
//                break;
//            case BOTTOM:
//                valign_itext = Element.ALIGN_BOTTOM;
//                break;
//        }
//        return valign_itext;
//    }
//
//    /**
//     * 颜色转化 - HSSFColor转化为BaseColor
//     *
//     * @param color
//     * @return
//     */
//    public static BaseColor parseColor(HSSFColor hssfColor) {
//        if (hssfColor == null) {
//            return new BaseColor(255, 255, 255);
//        }
//        short[] rgb = hssfColor.getTriplet();
//        return new BaseColor(rgb[0], rgb[1], rgb[2]);
//    }
//
//    /**
//     * 背景颜色转化（不支持黑色背景） - HSSFColor转化为BaseColor
//     *
//     * @param color
//     * @return
//     */
//    public static BaseColor parseBackgroundColor(HSSFColor hssfColor) {
//        if (hssfColor == null) {
//            // 白色
//            return new BaseColor(255, 255, 255);
//        }
//        short[] rgb = hssfColor.getTriplet();
//        if (rgb[0] == 0 && rgb[1] == 0 && rgb[2] == 0) {
//            rgb = new short[] { 255, 255, 255 };
//        }
//        return new BaseColor(rgb[0], rgb[1], rgb[2]);
//    }
//}
