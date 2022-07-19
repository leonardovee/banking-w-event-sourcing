package com.leonardovee.account.query.infrastructure.handlers;

import com.leonardovee.account.common.events.AccountClosedEvent;
import com.leonardovee.account.common.events.AccountOpenedEvent;
import com.leonardovee.account.common.events.FundsDepositedEvent;
import com.leonardovee.account.common.events.FundsWithdrawEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);

    void on(FundsDepositedEvent event);

    void on(FundsWithdrawEvent event);

    void on(AccountClosedEvent event);
}
