//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package vip.dcpay.cache.domain.config.db;

import com.alibaba.fastjson.JSON;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {
    private static final Logger log = LoggerFactory.getLogger(AspectConfig.class);

    public AspectConfig() {
    }

    @Before("@annotation(vip.dcpay.dao.db.Switch)")
    public void beforeDataSource(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        Switch sw = (Switch)method.getAnnotation(Switch.class);
        if (sw == null) {
            sw = (Switch)joinPoint.getTarget().getClass().getAnnotation(Switch.class);
        }

        DBTypeEnum value = sw.value();
        if (value.equals(DBTypeEnum.PLATFORM)) {
            DBContextHolder.platform();
        } else if (value.equals(DBTypeEnum.MERCHANT)) {
            DBContextHolder.merchant();
        } else if (value.equals(DBTypeEnum.ORDER)) {
            DBContextHolder.order();
        } else if (value.equals(DBTypeEnum.BASE)) {
            DBContextHolder.base();
        } else if (value.equals(DBTypeEnum.COMMON)) {
            DBContextHolder.common();
        } else if (value.equals(DBTypeEnum.SCHEDULE)) {
            DBContextHolder.schedule();
        } else if (value.equals(DBTypeEnum.SLAVE_BASE)) {
            DBContextHolder.slaveBase();
        }

        log.info("切换到数据库 【{}】", JSON.toJSONString(value));
    }

    @AfterReturning("@annotation(vip.dcpay.dao.db.Switch)")
    public void afterDataSource(JoinPoint joinPoint) {
        DBContextHolder.clearDB();
    }
}
