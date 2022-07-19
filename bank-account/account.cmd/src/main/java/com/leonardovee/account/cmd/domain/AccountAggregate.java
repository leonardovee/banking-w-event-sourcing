package com.leonardovee.account.cmd.domain;

import com.leonardovee.account.cmd.api.commands.OpenAccountCommand;
import com.leonardovee.account.common.events.AccountClosedEvent;
import com.leonardovee.account.common.events.AccountOpenedEvent;
import com.leonardovee.account.common.events.FundsDepositedEvent;
import com.leonardovee.account.common.events.FundsWithdrawEvent;
import com.leonardovee.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand command) {
        raiseEvent(AccountOpenedEvent.builder().id(command.getId()).accountHolder(command.getAccountHolder()).createdAt(new Date()).accountType(command.getAccountType()).openingBalance(command.getOpeningBalance()).build());
    }

    public double getBalance() {
        return this.balance;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be deposited in a closed bank account!");
        }
        if (amount <= 0) {
            throw new IllegalStateException("The deposit amount must be greater than zero!");
        }
        raiseEvent(FundsDepositedEvent.builder().id(this.id).amount(amount).build());
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void withdrawFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be withdraw from a closed bank account!");
        }
        raiseEvent(FundsWithdrawEvent.builder().id(this.id).amount(amount).build());
    }

    public void apply(FundsWithdrawEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void closeAccount() {
        if (!this.active) {
            throw new IllegalStateException("The bank account has already been closed!");
        }
        raiseEvent(AccountClosedEvent.builder().id(this.id).build());
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
