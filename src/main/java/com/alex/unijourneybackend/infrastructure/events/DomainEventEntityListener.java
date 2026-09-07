package com.alex.unijourneybackend.infrastructure.events;


import com.alex.unijourneybackend.common.events.domain.model.HasDomainEvents;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

public class DomainEventEntityListener {

    /**
     * Publishes domain events when an entity is persisted, updated or removed
     * @param entity the entity for which to publish events
     */
    @PostPersist
    @PostUpdate
    @PostRemove
    public void publishEvents(Object entity) {
        if (entity instanceof HasDomainEvents aggregate &&
                !aggregate.getDomainEvents().isEmpty()) {
            SpringContextHolder
                .getBean(DomainEventPublisher.class)
                .publish(aggregate.getDomainEvents());
            aggregate.clearEvents();
        }
    }


}
