

import com.spire.xls.FileFormat;
import com.spire.xls.Workbook;

import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) throws Exception {
// poi和itext将Excel转化为PDF（支持图片）
//        String pdf = "D:\\excel2pdf_new.pdf";
//        Xls2Pdf xls2Pdf = new Xls2Pdf("D:\\11111.xls", 0);
//        xls2Pdf.savePdf(pdf);
        // Create Workbook to load Excel file
//        String licenseFilePath = "excel-license.xml";
//        try {
//            InputStream is = Test.class.getClassLoader().getResourceAsStream(licenseFilePath);
//            System.out.println(is);
//            License license = new License();
//            license.setLicense(is);
//        } catch (Exception e) {
//            System.out.println("license verify failed");
//            e.printStackTrace();
//        }
//        Workbook workbook = new Workbook("D:/5555.xlsx");
//
//
//// Save the document in PDF format
//        workbook.save("D:/333.pdf", SaveFormat.PDF);

        //加载Excel文档
        Workbook wb = new Workbook();
        wb.loadFromFile("D:\\333.xlsx");

        //调用方法保存为PDF格式
        wb.saveToFile("D:\\333.pdf", FileFormat.PDF);
    }
}
