//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lw.common.security.impl;

import com.lw.common.exception.impl.AlreadyBuiltException;
import com.lw.common.security.SecurityBuilder;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractSecurityBuilder<O> implements SecurityBuilder<O> {
    private AtomicBoolean building = new AtomicBoolean();
    private O object;

    public AbstractSecurityBuilder() {
    }

    public final O build() throws Exception {
        if (this.building.compareAndSet(false, true)) {
            this.object = this.doBuild();
            return this.object;
        } else {
            throw new AlreadyBuiltException("This object has already been built");
        }
    }

    public final O getObject() {
        if (!this.building.get()) {
            throw new IllegalStateException("This object has not been built");
        } else {
            return this.object;
        }
    }

    protected abstract O doBuild() throws Exception;
}
