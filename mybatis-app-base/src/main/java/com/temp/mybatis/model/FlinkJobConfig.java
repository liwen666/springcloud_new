package com.temp.mybatis.model;

import java.util.Date;

public class FlinkJobConfig {
    private String queryId;

    private String resourceId;

    private String sqlQuery;

    private String functionPath;

    private String contentCode;

    private String createPerson;

    private String updatePerson;

    private Date createTime;

    private Date updateTime;

    private String flinkTables;

    private String udfFun;

    private String udafFun;

    private String udtfFun;

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public String getFunctionPath() {
        return functionPath;
    }

    public void setFunctionPath(String functionPath) {
        this.functionPath = functionPath;
    }

    public String getContentCode() {
        return contentCode;
    }

    public void setContentCode(String contentCode) {
        this.contentCode = contentCode;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getFlinkTables() {
        return flinkTables;
    }

    public void setFlinkTables(String flinkTables) {
        this.flinkTables = flinkTables;
    }

    public String getUdfFun() {
        return udfFun;
    }

    public void setUdfFun(String udfFun) {
        this.udfFun = udfFun;
    }

    public String getUdafFun() {
        return udafFun;
    }

    public void setUdafFun(String udafFun) {
        this.udafFun = udafFun;
    }

    public String getUdtfFun() {
        return udtfFun;
    }

    public void setUdtfFun(String udtfFun) {
        this.udtfFun = udtfFun;
    }
}