package com.leonardovee.cqrs.core.producers;

import com.leonardovee.cqrs.core.events.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
