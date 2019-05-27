//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.lw.common.security.ObjectPostProcessor;
import com.lw.common.security.SecurityBuilder;
import com.lw.common.security.SecurityConfigurer;
import io.jsonwebtoken.lang.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractConfiguredSecurityBuilder<O, B extends SecurityBuilder<O>> extends AbstractSecurityBuilder<O> {
    private final Log logger;
    private final LinkedHashMap<Class<? extends SecurityConfigurer<O, B>>, List<SecurityConfigurer<O, B>>> configurers;
    private final List<SecurityConfigurer<O, B>> configurersAddedInInitializing;
    private final Map<Class<? extends Object>, Object> sharedObjects;
    private final boolean allowConfigurersOfSameType;
    private AbstractConfiguredSecurityBuilder.BuildState buildState;
    private ObjectPostProcessor<Object> objectPostProcessor;

    protected AbstractConfiguredSecurityBuilder(ObjectPostProcessor<Object> objectPostProcessor) {
        this(objectPostProcessor, false);
    }

    protected AbstractConfiguredSecurityBuilder(ObjectPostProcessor<Object> objectPostProcessor, boolean allowConfigurersOfSameType) {
        this.logger = LogFactory.getLog(this.getClass());
        this.configurers = new LinkedHashMap();
        this.configurersAddedInInitializing = new ArrayList();
        this.sharedObjects = new HashMap();
        this.buildState = AbstractConfiguredSecurityBuilder.BuildState.UNBUILT;
        Assert.notNull(objectPostProcessor, "objectPostProcessor cannot be null");
        this.objectPostProcessor = objectPostProcessor;
        this.allowConfigurersOfSameType = allowConfigurersOfSameType;
    }

    public O getOrBuild() {
        if (this.isUnbuilt()) {
            try {
                return this.build();
            } catch (Exception var2) {
                this.logger.debug("Failed to perform build. Returning null", var2);
                return null;
            }
        } else {
            return this.getObject();
        }
    }



    public <C> void setSharedObject(Class<C> sharedType, C object) {
        this.sharedObjects.put(sharedType, object);
    }

    public <C> C getSharedObject(Class<C> sharedType) {
        return (C) this.sharedObjects.get(sharedType);
    }

    public Map<Class<? extends Object>, Object> getSharedObjects() {
        return Collections.unmodifiableMap(this.sharedObjects);
    }



    public <C extends SecurityConfigurer<O, B>> List<C> getConfigurers(Class<C> clazz) {
        List<C> configs = (List)this.configurers.get(clazz);
        return configs == null ? new ArrayList() : new ArrayList(configs);
    }

    public <C extends SecurityConfigurer<O, B>> List<C> removeConfigurers(Class<C> clazz) {
        List<C> configs = (List)this.configurers.remove(clazz);
        return configs == null ? new ArrayList() : new ArrayList(configs);
    }

    protected <P> P postProcess(P object) {
        return this.objectPostProcessor.postProcess(object);
    }



    protected void beforeInit() throws Exception {
    }

    protected void beforeConfigure() throws Exception {
    }

    protected abstract O performBuild() throws Exception;




    private Collection<SecurityConfigurer<O, B>> getConfigurers() {
        List<SecurityConfigurer<O, B>> result = new ArrayList();
        Iterator var2 = this.configurers.values().iterator();

        while(var2.hasNext()) {
            List<SecurityConfigurer<O, B>> configs = (List)var2.next();
            result.addAll(configs);
        }

        return result;
    }

    private boolean isUnbuilt() {
        synchronized(this.configurers) {
            return this.buildState == AbstractConfiguredSecurityBuilder.BuildState.UNBUILT;
        }
    }

    private static enum BuildState {
        UNBUILT(0),
        INITIALIZING(1),
        CONFIGURING(2),
        BUILDING(3),
        BUILT(4);

        private final int order;

        private BuildState(int order) {
            this.order = order;
        }

        public boolean isInitializing() {
            return INITIALIZING.order == this.order;
        }

        public boolean isConfigured() {
            return this.order >= CONFIGURING.order;
        }
    }
}
