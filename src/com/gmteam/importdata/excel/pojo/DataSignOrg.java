package com.gmteam.importdata.excel.pojo;

import com.gmteam.framework.core.model.BaseObject;

/** 
 * @author 
 * @version  
 * 类说明 
 */
@SuppressWarnings("serial")
public class DataSignOrg extends BaseObject {
    private String id;
    private String tableId;
    private String dataId;
    private String sign;
    private Object pkValue;
    public Object getPkValue() {
        return pkValue;
    }
    public void setPkValue(Object pkValue) {
        this.pkValue = pkValue;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTableId() {
        return tableId;
    }
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
    public String getDataId() {
        return dataId;
    }
    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) {
        this.sign = sign;
    }
}