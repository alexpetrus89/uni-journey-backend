package com.alex.unijourneybackend.common.domain.model;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SoftDeletableEntity<I extends Serializable>
        extends AuditableEntity<I> {

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    protected void markDeleted() {
        this.deleted = true;
    }

    protected void markUndeleted() {
        this.deleted = false;
    }


}
