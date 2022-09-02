package com.leonardovee.account.query.infrastructure.handlers;

import com.leonardovee.account.common.events.AccountClosedEvent;
import com.leonardovee.account.common.events.AccountOpenedEvent;
import com.leonardovee.account.common.events.FundsDepositedEvent;
import com.leonardovee.account.common.events.FundsWithdrawEvent;
import com.leonardovee.account.query.domain.AccountRepository;
import com.leonardovee.account.query.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountEventHandler implements EventHandler {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void on(AccountOpenedEvent event) {
        var bankAccount = BankAccount.builder().id(event.getId()).accountHolder(event.getAccountHolder()).creationDate(event.getCreatedAt()).accountType(event.getAccountType()).balance(event.getOpeningBalance()).build();
        accountRepository.save(bankAccount);
    }

    @Override
    public void on(FundsDepositedEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }
        // TODO: make this a UPDATE directly on the database instead of fetching the current value.
        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance + event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(FundsWithdrawEvent event) {
        var bankAccount = accountRepository.findById(event.getId());
        if (bankAccount.isEmpty()) {
            return;
        }
        var currentBalance = bankAccount.get().getBalance();
        var latestBalance = currentBalance - event.getAmount();
        bankAccount.get().setBalance(latestBalance);
        accountRepository.save(bankAccount.get());
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }
}
