package com.leonardovee.cqrs.core.handlers;

import com.leonardovee.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);

    T getById(String id);

    void republishEvents();
}
