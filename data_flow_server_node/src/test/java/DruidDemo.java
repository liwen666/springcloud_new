import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DruidDemo {

    @Test
    public void insertObj() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://192.168.42.136:3306/anyest3_1125?useUnicode=true");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.42.136:3306/anyest3_1125?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        try {
            // 获得连接:
            conn = dataSource.getConnection();
            // 编写SQL：
            String sql = "insert into testmaxwell (tempstamp_test,time,name) values (now(),now(),'测试')";
            pstmt = conn.prepareStatement(sql);
            // 执行sql:
          pstmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}