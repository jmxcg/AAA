package com.gmteam.spiritdata.upload.service;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.gmteam.spiritdata.importdata.excel.ExcelConstants;
import com.gmteam.spiritdata.importdata.excel.proxy.WorkBookProxy;

/** 
 * @author mht
 * @version  
 * 类说明 
 */
@Component
public class FileUploadService {
    /**
     * 
     * @param uploadFileName
     * @return
     * 根据文件名，返回int值，代表文件类型（暂时只支持excel，其他类型有待扩展）
     * 1--xls，2---。xlsx
     */
    public int getFileType(String uploadFileName) {
        int fileType = 0;
        String fileTypeStr = uploadFileName.substring(uploadFileName.lastIndexOf("."),uploadFileName.length());
        if(fileTypeStr.equals(".xls")){
            fileType = ExcelConstants.EXCEL_FILE_TYPE_HSSF;
        }else if(fileTypeStr.equals(".xlsx")){
            fileType = ExcelConstants.EXCEL_FILE_TYPE_XSSF;
        }
        return fileType;
    }
    /**workBook代理类*/
    private  WorkBookProxy workBookProxy;
    /**
     * 获取workBook,和MdList
     */
    public Object getFileMetaDate(String uploadFileName, int fileType) throws Exception {
        /**文件类型，要用于表判断返回来的workbook类型*/
        File excelFile = new File(uploadFileName);
        Object workBook = null;
        if(fileType==ExcelConstants.EXCEL_FILE_TYPE_HSSF){
            workBookProxy= new WorkBookProxy(excelFile,fileType);
            workBook = (HSSFWorkbook) workBookProxy.getWorkBook();
            workBookProxy.getMDList();
        }else if(fileType==ExcelConstants.EXCEL_FILE_TYPE_XSSF){
            workBookProxy= new WorkBookProxy(excelFile,fileType);
            workBook = (XSSFWorkbook) workBookProxy.getWorkBook();
            workBookProxy.getMDList();
        }
        return workBook;
    }
}
