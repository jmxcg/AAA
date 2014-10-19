package com.gmteam.spiritdata.importdata.excel.proxy;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gmteam.spiritdata.importdata.excel.util.PoiUtils;
import com.gmteam.spiritdata.importdata.excel.util.SheetInfo;
import com.gmteam.spiritdata.importdata.excel.util.pmters.MdPmters;
import com.gmteam.spiritdata.metadata.relation.pojo.MetadataModel;

/** 
 * @author mht
 * @version  
 * 类说明  适用于2007之后版本的excel(包含2007)
 */
public class XSSFWorkBookImpl implements IPoiUtils {
    /**workbook*/
    private XSSFWorkbook workbook;
    private int fileType;
    public XSSFWorkBookImpl() {  
    }
    public XSSFWorkBookImpl(File execlFile,int fileType) throws Exception{
        workbook = new XSSFWorkbook(new FileInputStream(execlFile));
        this.fileType = fileType;
    } 
    @Override
    public Object getWorkBook() {
        return workbook;
    }
    @Override
    public Object getMDList() throws Exception {
        List<Map<SheetInfo,MetadataModel>> mdModelMapList = new ArrayList<Map<SheetInfo,MetadataModel>>();
        int sheetSize = workbook.getNumberOfSheets();
        Object sheet;
        for(int i=0;i<sheetSize;i++ ){
            int sheetIndex = i;
            sheet = workbook.getSheetAt(sheetIndex);
            int rows = ((XSSFSheet) sheet).getLastRowNum()+1;
            if(rows+1>=2){
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
