package vip.dcpay.cache.domain.mgr;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.dcpay.cache.domain.service.RemoteService;
import vip.dcpay.log.sdk.MyLogManager;
import vip.dcpay.util.frame.spring.SpringContextUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ServiceManager {
    @Autowired
    private RemoteService remoteService;


    public long initMerchant() {
        long startTime = System.currentTimeMillis();
        MyLogManager.info("============================================初始化商户信息=============================================");
        long merchantCount = remoteService.initMerchant();
        MyLogManager.info("初始化获取商户数量：" + merchantCount + " 耗时：" + (System.currentTimeMillis() - startTime) + "ms");
        return merchantCount;
    }

    public void initTalble() {
        Connection conn = null;
        Statement stmt = null;
        try {
            DataSource dataSource = SpringContextUtil.getBean(DruidDataSource.class);
            conn = dataSource.getConnection();
            stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS merchant_info_cache");
            stmt.execute("  CREATE TABLE `merchant_info_cache` (\n" +
                    "        `id` bigint(32) DEFAULT NULL,\n" +
                    "        `uid` bigint(32) DEFAULT NULL,\n" +
                    "        `type` int(10) DEFAULT NULL,\n" +
                    "        `grab` int(1) DEFAULT NULL,\n" +
                    "        `player_deposit` int(1) DEFAULT NULL,\n" +
                    "        `platform_withdraw` int(1) DEFAULT NULL,\n" +
                    "        `merchant_deposit` int(1) DEFAULT NULL,\n" +
                    "        `merchant_withdraw` int(1) DEFAULT NULL,\n" +
                    "        `realname` varchar(255) DEFAULT NULL,\n" +
                    "        `activate_status` int(10) DEFAULT NULL,\n" +
//                    "        `payments` CLOB (6000) DEFAULT NULL,\n" +
//                    "        `payment_choices` CLOB (6000) DEFAULT NULL,\n" +
//                    "        `assets` CLOB(6000) DEFAULT NULL,\n" +
                    "        `payments` varchar (255) DEFAULT NULL,\n" +
                    "        `payment_choices` varchar (255) DEFAULT NULL,\n" +
                    "        `assets` varchar(1000) DEFAULT NULL,\n" +
                    "        `day_order_count` bigint(32) DEFAULT NULL,\n" +
                    "        `day_mount_sum` decimal(20,10) DEFAULT NULL\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int updateMerchantById(long id) {
        int result = remoteService.updateMerchant(id);
        return result;
    }
}
