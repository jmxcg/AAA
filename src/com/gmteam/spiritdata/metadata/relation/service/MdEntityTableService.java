package com.gmteam.spiritdata.metadata.relation.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gmteam.framework.core.dao.mybatis.MybatisDAO;
import com.gmteam.spiritdata.metadata.relation.pojo.MetadataModel;
import com.gmteam.spiritdata.metadata.relation.pojo.TableMapOrg;
import com.gmteam.spiritdata.util.SequenceUUID;

/**
 * 元数据实体表相关功能服务
 * @author wh
 */
@Component
public class MdEntityTableService {
    @Resource(name="defaultDAO")
    private MybatisDAO<TableMapOrg> tmoDao;

    @PostConstruct
    public void initParam() {
        tmoDao.setNamespace("mdTableMapOrg");
    }
    /**
     * 注册对应关系，并创建相应的表。
     * 注意此方法会改变参数mm中积累表的名称
     * @param tableName 实体表名称
     * @param mm 所根据的元数据信息
     * @param tableType 表类型
     * @return 映射关系表信息
     * @throws Exception
     */
    public TableMapOrg registTabOrgMap(String tableName, MetadataModel mm, int tableType)  throws Exception {
        if (tableName==null||tableName.equals("")) throw new IllegalArgumentException("实体表名称不能为空！");
        //创建相应的表
        //修改mm中的积累表名称
        if (tableType==1) mm.setTableName(tableName);
        //写入注册信息
        TableMapOrg insertTmo = new TableMapOrg();
        String newkey = SequenceUUID.getUUIDSubSegment(4);
        insertTmo.setId(newkey);
        insertTmo.setOwnerId(mm.getOwnerId());
        insertTmo.setMdMId(mm.getId());
        insertTmo.setTableName(tableName);
        insertTmo.setTableType(tableType);
        tmoDao.insert(insertTmo);
        insertTmo = tmoDao.getInfoObject("getInfoById", newkey);
        return insertTmo;
    }

    /**
     * 得到积累表信息
     * @param mdMId 元数据模式Id
     * @param ownerId 元数据所有者
     * @return 积累表信息
     * @throws Exception
     */
    public TableMapOrg getAccumulationTableMapOrg(String mdMId) throws Exception {
        if (mdMId==null||mdMId.equals("")) throw new IllegalArgumentException("元数据模式Id不能为空！");
        TableMapOrg paramTmo = new TableMapOrg();
        paramTmo.setMdMId(mdMId);
        paramTmo.setTableType(1);
        return tmoDao.getInfoObject(paramTmo);
    }
}