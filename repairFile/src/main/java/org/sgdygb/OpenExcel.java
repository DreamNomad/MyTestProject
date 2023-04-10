package org.sgdygb;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class OpenExcel {

    public static void main(String[] args) {
        try {
           // FileInputStream file = new FileInputStream(); // 打开Excel文件
            Workbook workbook = WorkbookFactory.create(Paths.get("D:\\1234.xlsx").toFile()); // 创建工作簿
            //Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
            //
            //// 获取行迭代器
            //Iterator<Row> rowIterator = sheet.iterator();
            //while (rowIterator.hasNext()) {
            //    Row row = rowIterator.next();
            //
            //    // 获取单元格迭代器
            //    Iterator<Cell> cellIterator = row.cellIterator();
            //
            //    //while (cellIterator.hasNext()) {
            //    //    Cell cell = cellIterator.next();
            //    //
            //    //    // 根据单元格类型处理数据
            //    //    switch (cell.getCellTypeEnum()) {
            //    //        case BOOLEAN:
            //    //            System.out.print(cell.getBooleanCellValue() + "\t");
            //    //            break;
            //    //        case NUMERIC:
            //    //            System.out.print(cell.getNumericCellValue() + "\t");
            //    //            break;
            //    //        case STRING:
            //    //            System.out.print(cell.getStringCellValue() + "\t");
            //    //            break;
            //    //        case FORMULA:
            //    //            System.out.print(cell.getCellFormula() + "\t");
            //    //            break;
            //    //        case BLANK:
            //    //            System.out.print("\t");
            //    //            break;
            //    //        default:
            //    //            System.out.print("\t");
            //    //    }
            //    //}
            //    System.out.println("");
            //}
            //file.close(); // 关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }

}

}
