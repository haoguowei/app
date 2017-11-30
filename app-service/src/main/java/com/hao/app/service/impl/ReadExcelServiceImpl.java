package com.hao.app.service.impl;

import com.hao.app.service.ReadExcelService;

import org.springframework.stereotype.Service;


import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * Created by haoguowei.
 * Time 2017/11/30 23:30
 * Desc
 */
@Service
public class ReadExcelServiceImpl implements ReadExcelService {

    private String savePath = "/Users/haoguowei/Downloads/";

    public static void main(String[] args) throws Exception {
        ReadExcelServiceImpl app = new ReadExcelServiceImpl();
        File file = new File("/Users/haoguowei/Downloads/2017.xls");
        // 获取文件后缀名
        String fileExt = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        InputStream input = new FileInputStream(file);
        app.readExcel(input, fileExt);
    }

    @Override
    public void readExcel(InputStream input, String fileExt) throws Exception {
        // 创建Workbook
        Workbook workbook = null;

        // 创建sheet
        Sheet sheet = null;

        //根据后缀判断excel 2003 or 2007+
        if (fileExt.equals("xls")) {
            workbook = WorkbookFactory.create(input);
        }else {
            workbook = new XSSFWorkbook(input);
        }

        //获取excel sheet总数
        int sheetNumbers = workbook.getNumberOfSheets();
        System.out.println("sheet个数:" + sheetNumbers);


        for (int i = 0; i < sheetNumbers; i++) {
            sheet = workbook.getSheetAt(i);

            //读取图片集合
            Map<String, String> pictureMap = getSheetMaps(i, workbook, sheet, fileExt);
            System.out.println("图片集合：" + pictureMap);
        }

        input.close();
    }


    private Map<String, String> getSheetMaps(int sheetIdx, Workbook workbook, Sheet sheet, String fileExt) throws IOException {
        Map<String, String> pictureDataMap = null;
        String dir = genNumb("");
        //根据后缀判断excel 2003 or 2007+
        if (fileExt.equals("xls")) {
            pictureDataMap = readImg2013(dir, sheetIdx, workbook, (HSSFSheet) sheet);
        }else {
            pictureDataMap = readImg2017(dir, sheetIdx, (XSSFSheet)sheet);
        }
        return pictureDataMap;
    }


    /**
     * 读取图片
     *
     * @param workbook
     * @param sheet
     */
    private Map<String, String> readImg2013(String dir, int sheetIdx, Workbook workbook, HSSFSheet sheet) throws IOException {
        Map<String, String> sheetIndexPicMap = new HashMap<String, String>();
        List<PictureData> pictures = (List<PictureData>) workbook.getAllPictures();
        if (pictures.size() != 0) {
            for (HSSFShape shape : sheet.getDrawingPatriarch().getChildren()) {
                HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                if (shape instanceof HSSFPicture) {
                    HSSFPicture pic = (HSSFPicture) shape;
                    int pictureIndex = pic.getPictureIndex() - 1;
                    HSSFPictureData picData = (HSSFPictureData) pictures.get(pictureIndex);
                    String picKey = genFileTitle(sheetIdx, anchor.getRow1(), anchor.getCol1());
                    String picPath = saveImg(dir, picData, picKey);
                    sheetIndexPicMap.put(picKey, picPath);
                }
            }
            return sheetIndexPicMap;
        }else {
            return null;
        }
    }


    /**
     * 读取图片
     * @param sheetIdx
     * @param sheet
     * @return
     */
    private Map<String, String> readImg2017(String dir, int sheetIdx, XSSFSheet sheet) {
        Map<String, String> sheetIndexPicMap = new HashMap<String, String>();
        for (POIXMLDocumentPart dr : sheet.getRelations()) {
            if (dr instanceof XSSFDrawing) {
                XSSFDrawing drawing = (XSSFDrawing) dr;
                List<XSSFShape> shapes = drawing.getShapes();
                for (XSSFShape shape : shapes) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getPreferredSize();
                    CTMarker ctMarker = anchor.getFrom();
                    String picKey = genFileTitle(sheetIdx, ctMarker.getRow(), ctMarker.getCol());
                    String picPath = saveImg(dir, pic.getPictureData(), picKey);
                    sheetIndexPicMap.put(picKey, picPath);
                }
            }
        }
        return sheetIndexPicMap;
    }


    private String genFileTitle(int sheetIdx, int row, int col){
        return String.valueOf(sheetIdx) + "_" + row + "_" + col;
    }


    private String saveImg(String dir, PictureData pic, String picName){
        FileOutputStream out = null;
        String ext = pic.suggestFileExtension();

        File dirFile = new File(savePath + dir);
        if (!dirFile.exists()){
            dirFile.mkdir();
        }
        String fullPath = savePath + dir + File.separator + picName + "." + ext;

        try {
            byte[] data = pic.getData();
            out = new FileOutputStream(fullPath);
            out.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fullPath;
    }

    @Override
    public String genNumb(String str){
        Random rand = new Random();
        // 获得[0,maxNumber]之间的随机数，然后取模获得[0, minNumber]之间的随机数
        Integer result = rand.nextInt(99999) % (99999 - 10000 + 1) + 10000;
        String numb = str + new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + result;
        return numb;
    }
}
