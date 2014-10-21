package com.gmteam.spiritdata.importdata.excel.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gmteam.spiritdata.importdata.excel.ExcelConstants;
import com.gmteam.spiritdata.importdata.excel.util.PoiUtils;
import com.gmteam.spiritdata.importdata.excel.util.SheetInfo;
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
    public HSSFWorkBookImpl(HSSFSheet hSheet,Map<Integer,Integer> delColIndexMap) {
        // TODO
    }
    @Override
    public Object getWorkBook() throws Exception {
        return workbook;
    }
    @Override
    public Object getMDList() throws Exception {
        Map<SheetInfo,MetadataModel> mdMap =PoiUtils.getMdModelMap(workbook,fileType);
        return mdMap;
    }
    @Override
    public Object getData() {
        // TODO Auto-generated method stub
        return null;
    }
}
