package vip;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.dcpay.h2.RunApplication;
import vip.dcpay.h2.application.User;
import vip.dcpay.h2.domain.config.H2Config;
import vip.dcpay.h2.infrastructure.dao.MerchantInfoDao;
import vip.dcpay.h2.infrastructure.model.MerchantInfo;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * author lw
 * date 2019/8/22  16:44
 * discribe
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RunApplication.class)
//@SpringBootApplication(exclude = {H2Config.class})
public class H2Test {
    @Autowired(required = false)
    private MerchantInfoDao merchantInfoDao;

    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("org.h2.Driver");
        druidDataSource.setUrl("jdbc:h2:tcp://192.168.1.170:8043/mem:testbpmn");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("testdcpaymysql@2019");
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

    public DataSource getmysqlDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("org.h2.Driver");
        druidDataSource.setUrl("jdbc:h2:tcp://192.168.1.170:8043/mem:testbpmn");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("testdcpaymysql@2019");
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
        System.out.println(dataSource);
        Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        long startTime = System.currentTimeMillis();
        long count = 10;
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
                    .uid((long) i)
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
    public void select() {
        MerchantInfo merchantInfo = merchantInfoDao.selectOne(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getUid,10000).apply( " 1=1 limit 1"));
        System.out.println(JSON.toJSONString(merchantInfo));
    }
    @Test
    public void insert() {
        int insert = merchantInfoDao.insert(MerchantInfo.builder().uid(10000l).realname("rest").activate_status(1).recv_pay_ways("FSAFDS").build());
        System.out.println(JSON.toJSONString(insert));
    }

    @Test
    public void temp() throws IllegalAccessException, InterruptedException {
        int weixin = jdbcTemplate.update(getInsertSql(MerchantInfo.builder().recv_pay_ways("weixin").uid(123l).build()));
        List<MerchantInfo> query = jdbcTemplate.query("select * from merchant_info", new BeanPropertyRowMapper(MerchantInfo.class));
        Thread.sleep(1000);
        System.out.println(JSON.toJSONString(query.get(0)));

    }
}
