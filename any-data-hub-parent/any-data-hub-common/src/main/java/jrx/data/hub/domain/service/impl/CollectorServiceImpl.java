package jrx.data.hub.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.domain.enums.ResourceType;
import jrx.data.hub.domain.enums.RespStatus;
import jrx.data.hub.domain.enums.VersionState;
import jrx.data.hub.domain.service.IMcUserService;
import jrx.data.hub.infrastructure.dao.McUserMapper;
import jrx.data.hub.infrastructure.entity.*;
import jrx.data.hub.util.CosineUtil;
import jrx.data.hub.util.DataResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 采集服务类
 * </p>
 *
 * @author lw
 * @since 2020-11-05
 */
@Service
@Slf4j
public class CollectorServiceImpl {

    @Autowired
    ZeppelinServiceImpl zeppelinServiceImpl;

    @Autowired
    private MetaDataObjectServiceImpl metaDataObjectServiceImpl;

    @Autowired
    private MetaDataObjectInfoServiceImpl metaDataObjectInfoServiceImpl;

    @Autowired
    private MetaDataSourceInfoServiceImpl metaDataSourceInfoServiceImpl;

    private double simiarVal = 0.9999;

    public void deal(String dataSourceId) {
        MetaDataSourceInfo metaDataSourceInfo = metaDataSourceInfoServiceImpl.getOne(Wrappers.<MetaDataSourceInfo>lambdaQuery().eq(MetaDataSourceInfo::getDataSourceId, dataSourceId));
        //初始化采集表信息逻辑
        initTable(metaDataSourceInfo);
        //升版本逻辑
        QueryWrapper<MetaDataObjectInfo> infoParam = new QueryWrapper<>();
        infoParam.eq("data_source_id", dataSourceId);
        List<MetaDataObjectInfo> infoList = metaDataObjectInfoServiceImpl.list(infoParam);
        for (MetaDataObjectInfo metaDataObjectInfo : infoList) {
            QueryWrapper<MetaDataObject> listParam = new QueryWrapper<>();
            listParam.eq("resource_id", metaDataObjectInfo.getResourceId());
            listParam.orderByDesc("create_time");
            List<MetaDataObject> list = metaDataObjectServiceImpl.list(listParam);
            MetaDataObject lastMetaDataObject = list.get(0);
            String newDdlSql = "";
            if (metaDataSourceInfo.getDbType() == DbType.MYSQL) {
                newDdlSql = getMysqlDdl(metaDataSourceInfo.getSourceName(), metaDataObjectInfo);
            } else if (metaDataSourceInfo.getDbType() == DbType.GREENPLUM) {
                newDdlSql = getGreenPlumDdl(metaDataSourceInfo.getSourceName(), metaDataObjectInfo);
            }
            if (StringUtils.isNotEmpty(newDdlSql)) {
                bwtDDLSql(lastMetaDataObject, newDdlSql);
            }
        }
    }

    /**
     * 初始化表结构
     */
    public void initTable(MetaDataSourceInfo metaDataSourceInfo) {
        String sql = "";
        if (metaDataSourceInfo.getDbType() == DbType.MYSQL) {
            sql = "show tables";
        } else if (metaDataSourceInfo.getDbType() == DbType.GREENPLUM) {
            sql = "SELECT table_name FROM information_schema.tables  where table_schema='public'";
        }
        DataResponse dataResponse = zeppelinServiceImpl.execCollectDataSourceInfoJob("/" + metaDataSourceInfo.getSourceName(), metaDataSourceInfo, "%" + metaDataSourceInfo.getSourceName() + "\n" +
                "\n" +
                sql);
        if (dataResponse.getStatus() == RespStatus.SUCCESS) {
            String data = getResponseData(dataResponse);
            String[] tableNames = data.split("\n");
            for (int i = 1; i < tableNames.length; i++) {
                String tName = tableNames[i];
                QueryWrapper<MetaDataObjectInfo> param = new QueryWrapper<>();
                param.eq("name", tName);
                MetaDataObjectInfo metaDataObjectInfo = metaDataObjectInfoServiceImpl.getOne(param);
                if (metaDataObjectInfo == null) {
                    metaDataObjectInfo = new MetaDataObjectInfo();
                    metaDataObjectInfo.setName(tName);
                    metaDataObjectInfo.setDataSourceId(metaDataSourceInfo.getDataSourceId());
                    metaDataObjectInfo = metaDataObjectInfoServiceImpl.createCollectTable(metaDataObjectInfo);
                }
            }
        }
    }


    /**
     * 获取mysql ddl
     */
    public String getMysqlDdl(String sourceName, MetaDataObjectInfo metaDataObjectInfo) {
        DataResponse dataResponse = zeppelinServiceImpl.execCollectObjectInfoJob("/" + sourceName, metaDataObjectInfo, "%" + sourceName + "\n" +
                "\n" +
                "show create table " + metaDataObjectInfo.getName());
        String ddlSql = "";
        if (dataResponse.getStatus() == RespStatus.SUCCESS) {
            ddlSql = getResponseData(dataResponse);
            ddlSql = "CREATE " + ddlSql.split("CREATE")[1];
        }
        return ddlSql;
    }

    /**
     * 获取gp ddl
     */
    public String getGreenPlumDdl(String sourceName, MetaDataObjectInfo metaDataObjectInfo) {
        String sql = "SELECT table_name,column_name,udt_name,character_maximum_length,numeric_precision,datetime_precision FROM information_schema.columns WHERE table_name='";
        DataResponse dataResponse = zeppelinServiceImpl.execCollectObjectInfoJob("/" + sourceName, metaDataObjectInfo, "%" + sourceName + "\n" +
                "\n" +
                sql + metaDataObjectInfo.getName() + "'");
        String ddlSql = "";
        if (dataResponse.getStatus() == RespStatus.SUCCESS) {
            String data = getResponseData(dataResponse);
            String[] lines = data.split("\n");
            StringBuilder sb = new StringBuilder();
            sb.append("create table " + metaDataObjectInfo.getName() + " (");
            for (int i = 1; i < lines.length; i++) {
                String[] line = lines[i].split("\t");
                String cName = line[1];
                String cType = line[2];
                String length = "";
                String cVarLength = line[3];
                String cNumLength = line[4];
                String cDateLength = line[5];
                if (!"null".equals(cVarLength)) {
                    length = cVarLength;
                } else if (!"null".equals(cNumLength)) {
                    length = cNumLength;
                } else if (!"null".equals(cDateLength)) {
                    length = cDateLength;
                }
                sb.append(cName + " " + cType + "(" + length + ")");
                if (i != lines.length - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
            ddlSql = sb.toString();
        }
        return ddlSql;
    }

    public void bwtDDLSql(MetaDataObject lastVersionMetaDataObject, String newDdlSql) {
        try {
            Map<String, Integer> map1 = CosineUtil.participle(lastVersionMetaDataObject.getDdlSql());
            Map<String, Integer> map2 = CosineUtil.participle(newDdlSql);
            if (CosineUtil.similarity(map1, map2) <= simiarVal) {
                log.info("定时器mysql数据库:{},表:{},旧ddlSql:{}", lastVersionMetaDataObject.getDdlSql());
                log.info("定时器mysql数据库:{},表:{},新ddlSql:{}", newDdlSql);
                log.info("相似度为:{},小于:{},开始升版本", CosineUtil.similarity(map1, map2), simiarVal);
                //升版本
                lastVersionMetaDataObject.setDdlSql(newDdlSql);
                lastVersionMetaDataObject = metaDataObjectServiceImpl.createNewVersion(lastVersionMetaDataObject);
                log.info("定时器升版本成功,最新版本号:{}", lastVersionMetaDataObject.getVersionCode());
            }
        } catch (Exception e) {
            log.error("相似度对比失败", e);
        }
    }

    public String getResponseData(DataResponse dataResponse) {
        JSONArray jsonArray = JSONObject.parseArray(JSON.toJSONString(dataResponse.getData()));
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        String data = jsonObject.getString("data");
        return data;
    }
}
