package com.gmteam.spiritdata.metadata.relation.pojo;

import java.sql.Timestamp;

import com.gmteam.framework.core.model.BaseObject;

/**
 * 元数据列语义
 * 对应持久化中数据库的表为SA_MD_COLSEMANTEME
 * @author wh, mht
 */
public class MetadataColSemanteme extends BaseObject {
    private static final long serialVersionUID = 4955422640865996870L;

    private String id; //列语义Id
    private String colId; //列Id
    private String mdMId; //元数据模式Id
    private Integer semantemeCode; //语义代码
    private Integer semantemeType; //语义类型
    private Timestamp cTime; //记录创建时间
    private Timestamp lmTime; //最后修改时间
    //以上对应数据库信息
    private MetadataColumn column; //本列语义对应的列描述信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColId() {
        return colId;
    }

    public void setColId(String colId) {
        this.colId = colId;
    }

    public String getMdMId() {
        return mdMId;
    }

    public void setMdMId(String mdMId) {
        this.mdMId = mdMId;
    }

    public Integer getSemantemeCode() {
        return semantemeCode;
    }

    public void setSemantemeCode(Integer semantemeCode) {
        this.semantemeCode = semantemeCode;
    }

    public Integer getSemantemeType() {
        return semantemeType;
    }

    public void setSemantemeType(Integer semantemeType) {
        this.semantemeType = semantemeType;
    }

    public Timestamp getcTime() {
        return cTime;
    }

    public void setcTime(Timestamp cTime) {
        this.cTime = cTime;
    }

    public Timestamp getLmTime() {
        return lmTime;
    }

    public void setLmTime(Timestamp lmTime) {
        this.lmTime = lmTime;
    }

    public MetadataColumn getColumn() {
        return column;
    }

    public void setColumn(MetadataColumn column) {
        this.colId=column.getId();
        this.mdMId=column.getMdMId();
        if (column.getMdModel()!=null) this.mdMId=column.getMdModel().getId();
        this.column = column;
    }
}