//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lw.common.security.ObjectPostProcessor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.util.Assert;

final class AutowireBeanFactoryObjectPostProcessor implements ObjectPostProcessor<Object>, DisposableBean, SmartInitializingSingleton {
    private final Log logger = LogFactory.getLog(this.getClass());
    private final AutowireCapableBeanFactory autowireBeanFactory;
    private final List<DisposableBean> disposableBeans = new ArrayList();
    private final List<SmartInitializingSingleton> smartSingletons = new ArrayList();

    public AutowireBeanFactoryObjectPostProcessor(AutowireCapableBeanFactory autowireBeanFactory) {
        Assert.notNull(autowireBeanFactory, "autowireBeanFactory cannot be null");
        this.autowireBeanFactory = autowireBeanFactory;
    }

    public <T> T postProcess(T object) {
        if (object == null) {
            return null;
        } else {
            Object result = null;

            try {
                result = this.autowireBeanFactory.initializeBean(object, object.toString());
            } catch (RuntimeException var5) {
                Class<?> type = object.getClass();
                throw new RuntimeException("Could not postProcess " + object + " of type " + type, var5);
            }

            this.autowireBeanFactory.autowireBean(object);
            if (result instanceof DisposableBean) {
                this.disposableBeans.add((DisposableBean)result);
            }

            if (result instanceof SmartInitializingSingleton) {
                this.smartSingletons.add((SmartInitializingSingleton)result);
            }

            return (T) result;
        }
    }

    public void afterSingletonsInstantiated() {
        Iterator var1 = this.smartSingletons.iterator();

        while(var1.hasNext()) {
            SmartInitializingSingleton singleton = (SmartInitializingSingleton)var1.next();
            singleton.afterSingletonsInstantiated();
        }

    }

    public void destroy() throws Exception {
        Iterator var1 = this.disposableBeans.iterator();

        while(var1.hasNext()) {
            DisposableBean disposable = (DisposableBean)var1.next();

            try {
                disposable.destroy();
            } catch (Exception var4) {
                this.logger.error(var4);
            }
        }

    }
}
