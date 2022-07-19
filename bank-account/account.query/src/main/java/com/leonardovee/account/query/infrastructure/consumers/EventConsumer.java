package com.leonardovee.account.query.infrastructure.consumers;

import com.leonardovee.account.common.events.AccountClosedEvent;
import com.leonardovee.account.common.events.AccountOpenedEvent;
import com.leonardovee.account.common.events.FundsDepositedEvent;
import com.leonardovee.account.common.events.FundsWithdrawEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consume(@Payload AccountOpenedEvent event, Acknowledgment ack);

    void consume(@Payload FundsDepositedEvent event, Acknowledgment ack);

    void consume(@Payload FundsWithdrawEvent event, Acknowledgment ack);

    void consume(@Payload AccountClosedEvent event, Acknowledgment ack);
}
