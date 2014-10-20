package com.gmteam.spiritdata.importdata.excel.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gmteam.spiritdata.importdata.excel.ExcelConstants;
import com.gmteam.spiritdata.importdata.excel.util.PoiUtils;
import com.gmteam.spiritdata.importdata.excel.util.SheetInfo;
import com.gmteam.spiritdata.importdata.excel.util.pmters.MdPmters;
import com.gmteam.spiritdata.metadata.relation.pojo.MetadataModel;

/** 
 * @author mht
 * @version   
 * 类说明  适用于2007之前版本的excel(不包含2007)
 */
public class HSSFWorkBookImpl implements IPoiUtils{
    private int fileType = ExcelConstants.EXCEL_FILE_TYPE_HSSF;
    private HSSFWorkbook workbook;
    public HSSFWorkBookImpl() {  
    }
    public HSSFWorkBookImpl(File execlFile) throws Exception{
        workbook = new HSSFWorkbook(new FileInputStream(execlFile)); 
    } 
    @Override
    public Object getWorkBook() throws Exception {
        return workbook;
    }
    @Override
    public Object getMDList() throws Exception {
        List<Map<SheetInfo,MetadataModel>> mdModelMapList = new ArrayList<Map<SheetInfo,MetadataModel>>();
        int sheetSize = workbook.getNumberOfSheets();
        HSSFSheet sheet;
        boolean isActive;
        for(int i=0;i<sheetSize;i++ ){
            int sheetIndex = i;
            sheet = workbook.getSheetAt(sheetIndex);
            isActive = sheet.isActive();
            int rows = sheet.getLastRowNum()+1;
            if(isActive==false&&rows+1>=2){
                /**
                 * 根据条数分析MateData
                 */
                MdPmters mdPmters = new MdPmters();
                mdPmters.setFileType(1);
                mdPmters.setSheet(sheet);
                Map<SheetInfo,MetadataModel> mdModelMap =  PoiUtils.getMdModelMap(sheet,sheetIndex,fileType);
                mdModelMapList.add(mdModelMap);
            }
        }
        return null;
    }
}
