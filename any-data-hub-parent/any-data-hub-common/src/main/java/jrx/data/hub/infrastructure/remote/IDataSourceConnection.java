package jrx.data.hub.infrastructure.remote;

import jrx.data.hub.domain.enums.DbType;
import jrx.data.hub.domain.exception.DataSourceException;
import jrx.data.hub.infrastructure.entity.MetaObjectField;

import java.util.List;

/**
 * 数据源连接管理
 * 只执行DDL 不涉及DML
 */
public interface IDataSourceConnection<T> {

    /**
     * 建立连接
     * @param force
     */
    void connect(boolean force) throws DataSourceException;

    /**
     * 获取对应的连接管理类
     * @return
     */
    T getConnect();

    /**
     * 关闭连接
     */
    void close();

    /**
     * 获取数据源类型
     * @return
     */
    DbType getDbType();


    /**判断表信息是否存在 */
    boolean existTable(String tableName);

    /**
     * 连通测试
     * @return
     */
    Boolean connectionTest() throws DataSourceException;

}
