import com.alibaba.druid.pool.DruidDataSource;
import com.example.thymeleafdemo.config.UeduitorManager;
import com.example.thymeleafdemo.entity.MerchantInfo;
import org.junit.Test;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * author lw
 * date 2019/8/22  16:44
 * discribe
 */
public class H2Test {
    @Test
    public void name() throws SQLException {
        DataSource dataSource = getDataSource();
        UeduitorManager.dataSource = dataSource;
        System.out.println(dataSource);


        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        //如果存在USER_INFO表就先删除USER_INFO表
        stmt.execute("DROP TABLE IF EXISTS USER_INFO");
        //创建USER_INFO表
        stmt.execute("CREATE TABLE USER_INFO(id VARCHAR(36) PRIMARY KEY,name VARCHAR(100),sex VARCHAR(4))");
        //新增
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','大日如来','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','青龙','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','白虎','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','朱雀','女')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','玄武','男')");
        stmt.executeUpdate("INSERT INTO USER_INFO VALUES('" + UUID.randomUUID() + "','苍狼','男')");
        //删除
        stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
        //修改
        stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
        //查询
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER_INFO");
        //遍历结果集
        while (rs.next()) {
            System.out.println(rs.getString("id") + "," + rs.getString("name") + "," + rs.getString("sex"));
        }
        stmt.execute("DROP TABLE IF EXISTS hq_protal_bytearray");
        //创建USER_INFO表
        stmt.execute("create table hq_protal_bytearray \n" +
                "(\n" +
                "   id                 VARCHAR2(64),\n" +
                "   name               VARCHAR2(64),\n" +
                "   bytes              BLOB,\n" +
//                "   bytes              longvarbinary,\n" +
                "   belongid           VARCHAR2(64),\n" +
                "   belongtype         VARCHAR2(64),\n" +
                "   time               TIMESTAMP(6)\n" +
                ");");
        System.out.println("初始化表 " + "hq_protal_bytearray");
        //释放资源
        stmt.close();
        conn.close();


    }

    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("org.h2.Driver");
//        druidDataSource.setUrl("jdbc:h2:tcp://192.168.1.170:8043/mem:testbpmn");
        druidDataSource.setUrl("jdbc:h2:mem:testbpmn");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
//        基本属性 url、user、password
//                <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
//        <property name="url" value="jdbc:mysql://192.168.9.180:3306/hqbpmn?useUnicode=true"/>
//        <property name="username" value="root"/>
//        <property name="password" value="root"/>
//
//                配置初始化大小、最小、最大
//                <property name="initialSize" value="10"/>
//        <property name="minIdle" value="10"/>
//        <property name="maxActive" value="50"/>
//                配置获取连接等待超时的时间
//                <property name="maxWait" value="60000"/>
//                配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//                <property name="timeBetweenEvictionRunsMillis" value="60000"/>
//
//                配置一个连接在池中最小生存的时间，单位是毫秒
//                <property name="minEvictableIdleTimeMillis" value="300000"/>
//
//        <property name="validationQuery" value="SELECT 'x'"/>
//        <property name="testWhileIdle" value="true"/>
//        <property name="testOnBorrow" value="false"/>
//        <property name="testOnReturn" value="false"/>
//
//                打开PSCache，并且指定每个连接上PSCache的大小  如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。
//        <property name="poolPreparedStatements" value="false"/>
//        <property name="maxPoolPreparedStatementPerConnectionSize" value="20"/>
//
//                配置监控统计拦截的filters
//                <property name="filters" value="wall,stat"/>
        return druidDataSource;
    }

    public DataSource getMysqlDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        druidDataSource.setUrl("jdbc:h2:tcp://192.168.1.170:8043/mem:testbpmn");
        druidDataSource.setUrl("jdbc:mysql://192.168.42.136:3306/dcpay_merchant?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowMultiQueries=true");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
//        基本属性 url、user、password
//                <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
//        <property name="url" value="jdbc:mysql://192.168.9.180:3306/hqbpmn?useUnicode=true"/>
//        <property name="username" value="root"/>
//        <property name="password" value="root"/>
//
//                配置初始化大小、最小、最大
//                <property name="initialSize" value="10"/>
//        <property name="minIdle" value="10"/>
//        <property name="maxActive" value="50"/>
//                配置获取连接等待超时的时间
//                <property name="maxWait" value="60000"/>
//                配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
//                <property name="timeBetweenEvictionRunsMillis" value="60000"/>
//
//                配置一个连接在池中最小生存的时间，单位是毫秒
//                <property name="minEvictableIdleTimeMillis" value="300000"/>
//
//        <property name="validationQuery" value="SELECT 'x'"/>
//        <property name="testWhileIdle" value="true"/>
//        <property name="testOnBorrow" value="false"/>
//        <property name="testOnReturn" value="false"/>
//
//                打开PSCache，并且指定每个连接上PSCache的大小  如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。
//        <property name="poolPreparedStatements" value="false"/>
//        <property name="maxPoolPreparedStatementPerConnectionSize" value="20"/>
//
//                配置监控统计拦截的filters
//                <property name="filters" value="wall,stat"/>
        return druidDataSource;
    }

    @Test
    public void testefficient() throws SQLException, IllegalAccessException {
        System.out.println("=========================================================================================");
        DataSource dataSource = getDataSource();
        UeduitorManager.dataSource = dataSource;
        System.out.println(dataSource);
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        long startTime = System.currentTimeMillis();
        long count = 1000000;
        //如果存在USER_INFO表就先删除USER_INFO表
        stmt.execute("DROP TABLE IF EXISTS merchant_info");
        //创建USER_INFO表
        stmt.execute("  CREATE TABLE `merchant_info` (\n" +
                "                `id` bigint(32) DEFAULT NULL,\n" +
                "        `uid` bigint(32) DEFAULT NULL,\n" +
                "        `type` int(10) DEFAULT NULL,\n" +
                "        `realname` varchar(255) DEFAULT NULL,\n" +
                "        `activate_status` int(10) DEFAULT NULL,\n" +
                "        `recv_pay_ways` varchar(2000) DEFAULT NULL,\n" +
                "        `assets` varchar(2000) DEFAULT NULL,\n" +
                "        `day_mount_sum` decimal(20,10) DEFAULT NULL\n" +
                ")");

        for (int i = 0; i < count; i++) {
            String sql = getInsertSql(MerchantInfo.builder().activate_status(1).assets("{}").day_mount_sum(new BigDecimal(1000))
                    .id(1l).uid((long) i)
                    .realname("test").recv_pay_ways("weixin").type(1).build());
            stmt.executeUpdate(sql);

        }
//        System.out.println("插入数据 "+count+"  条 耗时"+(System.currentTimeMillis()-startTime));
        //删除
//            stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
        //修改
//            stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
        //查询
        long queryStart = System.currentTimeMillis();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM MERCHANT_INFO ");
        ResultSet rs = stmt.executeQuery("SELECT * FROM MERCHANT_INFO limit 100 ");
        //遍历结果集
            while (rs.next()) {
                Field[] declaredFields = MerchantInfo.class.getDeclaredFields();
                for(Field f:declaredFields){
                    System.out.print(rs.getString(f.getName()));
                }
            }
        System.out.println("查询耗时 ：" +(System.currentTimeMillis()-queryStart));
            System.out.println("=========================================================================================");
        //释放资源
        stmt.close();
        conn.close();
    }

    private String getInsertSql(MerchantInfo build) throws IllegalAccessException {
//        insert into MERCHANT_INFO (id,uid,type,realname) values (1,11,1,'我')
            String sql = "insert into MERCHANT_INFO (";
        Field[] declaredFields = build.getClass().getDeclaredFields();
        for(Field f:declaredFields){
            sql+=f.getName()+",";
        }
        sql =sql.substring(0,sql.length()-1)+") values( ";
        for(Field f:declaredFields){
            f.setAccessible(true);
            if(f.getType().getName().equals("java.lang.String")){
                sql+= "'"+f.get(build)+"',";
                continue;
            }
            sql+=f.get(build)+",";
        }
        sql =sql.substring(0,sql.length()-1)+")";
        return sql;
    }



    @Test
    public void testefficientMsql() throws SQLException, IllegalAccessException {
        System.out.println("=========================================================================================");
        DataSource dataSource = getMysqlDataSource();
        UeduitorManager.dataSource = dataSource;
        System.out.println(dataSource);
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        long startTime = System.currentTimeMillis();
        long count = 1000000;
        //如果存在USER_INFO表就先删除USER_INFO表

        for (int i = 0; i < count; i++) {
            String sql = getInsertSql(MerchantInfo.builder().activate_status(1).assets("{}").day_mount_sum(new BigDecimal(1000))
                    .id(1l).uid((long) i)
                    .realname("test").recv_pay_ways("weixin").type(1).build());
            stmt.executeUpdate(sql);

        }
        System.out.println("插入数据 "+count+"  条 耗时"+(System.currentTimeMillis()-startTime));
        //删除
//            stmt.executeUpdate("DELETE FROM USER_INFO WHERE name='大日如来'");
        //修改
//            stmt.executeUpdate("UPDATE USER_INFO SET name='孤傲苍狼' WHERE name='苍狼'");
        //查询
        long queryStart = System.currentTimeMillis();
        ResultSet rs = stmt.executeQuery("SELECT * FROM MERCHANT_INFO where uid=11");
        //遍历结果集
        while (rs.next()) {
            Field[] declaredFields = MerchantInfo.class.getDeclaredFields();
            for(Field f:declaredFields){
                System.out.print(rs.getString(f.getName()));
            }
        }
        System.out.println("查询耗时 ：" +(System.currentTimeMillis()-queryStart));
        System.out.println("=========================================================================================");
        //释放资源
        stmt.close();
        conn.close();
    }
}
