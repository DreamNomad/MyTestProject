//package com.apiao;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
//import org.apache.poi.hssf.usermodel.HSSFPatriarch;
//import org.apache.poi.hssf.usermodel.HSSFPicture;
//import org.apache.poi.hssf.usermodel.HSSFPictureData;
//import org.apache.poi.hssf.usermodel.HSSFShape;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
///**
// * Excel2003的常用方法
// *
// * @author zyj
// * @date 2019-10-14
// */
//public class XlsHelper {
//    /**
//     * 根据图片主键获取图片所在位置
//     *
//     * @param key，格式： sheet索引_起始行号_起始列号_结束行号_结束列号_单元格内的左边距_上边距_右边距_下边距_uuid
//     * @return sheet索引，起始行号，起始列号，结束行号，结束列号，左边距，上边距，右边距，下边距
//     */
//    public static int[] getImgPostion(String imgKey) {
//        String[] arr = StringUtils.split(imgKey, "_");
//        int[] position = new int[9];
//        for (int i = 0; i < 9; i++) {
//            position[i] = Integer.parseInt(arr[i]);
//        }
//        return position;
//    }
//
//    /**
//     * Excel的图片获取
//     *
//     * @param wb Excel的工作簿
//     * @return Excel的图片，键格式：sheet索引_起始行号_起始列号_结束行号_结束列号_单元格内的左边距_上边距_右边距_下边距_uuid
//     */
//    public static Map<String, HSSFPictureData> getPictrues(HSSFWorkbook wb) {
//        Map<String, HSSFPictureData> map = new HashMap<String, HSSFPictureData>();
//        // getAllPictures方法只能获取不同的图片，如果Excel中存在相同的图片，只能得到一张图片
//        List<HSSFPictureData> pics = wb.getAllPictures();
//        if (pics.size() == 0) {
//            return map;
//        }
//        for (Integer sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
//            HSSFSheet sheet = wb.getSheetAt(sheetIndex);
//            HSSFPatriarch patriarch = sheet.getDrawingPatriarch();
//            if (patriarch == null) {
//                continue;
//            }
//            for (HSSFShape shape : patriarch.getChildren()) {
//                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
//                if (shape instanceof HSSFPicture) {
//                    HSSFPicture pic = (HSSFPicture) shape;
//                    int picIndex = pic.getPictureIndex() - 1;
//                    HSSFPictureData picData = pics.get(picIndex);
//                    // 键格式：sheet索引_行号_列号_单元格内的上边距_单元格内的左边距_uuid
//                    String key = sheetIndex + "_" + anchor.getRow1() + "_" + anchor.getCol1() + "_" + anchor.getRow2() + "_" + anchor.getCol2();
//                    key += "_" + anchor.getDx1() + "_" + anchor.getDy1() + "_" + anchor.getDx2() + "_" + anchor.getDy2();
//                    key += "_" + UUID.randomUUID();
//                    map.put(key, picData);
//                }
//            }
//        }
//        return map;
//    }
//
//    /**
//     * Excel的图片获取
//     *
//     * @param wb         Excel的工作簿
//     * @param sheetIndex sheet索引
//     * @return Excel的图片，键格式：sheet索引_起始行号_起始列号_结束行号_结束列号_单元格内的左边距_上边距_右边距_下边距_uuid
//     */
//    public static Map<String, HSSFPictureData> getPictrues(HSSFWorkbook wb, int sheetIndex) {
//        Map<String, HSSFPictureData> map = new HashMap<String, HSSFPictureData>();
//        // getAllPictures方法只能获取不同的图片，如果Excel中存在相同的图片，只能得到一张图片
//        List<HSSFPictureData> pics = wb.getAllPictures();
//        if (pics.size() == 0) {
//            return map;
//        }
//        HSSFSheet sheet = wb.getSheetAt(sheetIndex);
//        HSSFPatriarch patriarch = sheet.getDrawingPatriarch();
//        if (patriarch == null) {
//            return map;
//        }
//        for (HSSFShape shape : patriarch.getChildren()) {
//            HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
//            if (shape instanceof HSSFPicture) {
//                HSSFPicture pic = (HSSFPicture) shape;
//                int picIndex = pic.getPictureIndex() - 1;
//                HSSFPictureData picData = pics.get(picIndex);
//                // 键格式：sheet索引_起始行号_起始列号_结束行号_结束列号_单元格内的左边距_上边距_右边距_下边距_uuid
//                String key = sheetIndex + "_" + anchor.getRow1() + "_" + anchor.getCol1() + "_" + anchor.getRow2() + "_" + anchor.getCol2();
//                key += "_" + anchor.getDx1() + "_" + anchor.getDy1() + "_" + anchor.getDx2() + "_" + anchor.getDy2();
//                key += "_" + UUID.randomUUID();
//                map.put(key, picData);
//            }
//        }
//        return map;
//    }
//}
