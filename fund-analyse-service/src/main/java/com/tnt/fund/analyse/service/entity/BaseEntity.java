package com.tnt.fund.analyse.service.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 6215943942076419246L;

    private long createTime;

    private long lastModified;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
