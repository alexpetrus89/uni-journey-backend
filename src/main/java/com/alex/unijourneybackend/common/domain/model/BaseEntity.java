package com.alex.unijourneybackend.common.domain.model;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity<I extends Serializable> {

    @Version
    private Long version;

    protected Long getVersion() {
        return version;
    }

    public abstract I getId();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity<?> other)) return false;
        return getId() != null && getId().equals(other.getId());
    }

    @Override
    public final int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }


}
