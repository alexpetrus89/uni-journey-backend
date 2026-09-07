package com.alex.unijourneybackend.common.events.domain.model;


import java.util.List;

public interface HasDomainEvents {

    /**
     * Returns a list of domain events
     * @return
     */
    List<DomainEvent> getDomainEvents();

    /**
     * Clears the list of domain events
     */
    void clearEvents();

}
