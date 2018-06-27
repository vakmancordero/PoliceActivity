package com.bsdenterprise.qbits.policeactivity.common.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private boolean softDelete;

    private Timestamp modifiedAt;

    @Column(updatable = false)
    private Timestamp createdAt;

    @Column(updatable = false)
    private String uuid;

    @PrePersist
    public void setAuditInfo() {
        Calendar current = Calendar.getInstance();
        this.setCreatedAt(new Timestamp(current.getTimeInMillis()));
        this.setModifiedAt(new Timestamp(current.getTimeInMillis()));
        if (StringUtils.isEmpty(uuid)) this.setUuid(UUID.randomUUID().toString());
    }

    @PreUpdate
    public void setModifyInfo() {
        Calendar current = Calendar.getInstance();
        this.setModifiedAt(new Timestamp(current.getTimeInMillis()));
    }

}