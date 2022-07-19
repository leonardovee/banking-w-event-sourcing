package com.leonardovee.account.query.api.queries;

import com.leonardovee.account.query.api.dto.EqualityType;
import com.leonardovee.account.query.domain.AccountRepository;
import com.leonardovee.account.query.domain.BankAccount;
import com.leonardovee.cqrs.core.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountQueryHandler implements QueryHandler {
    @Autowired
    private AccountRepository repository;

    @Override
    public List<BaseEntity> handle(FindAllAccountsQuery query) {
        Iterable<BankAccount> bankAccounts = repository.findAll();
        List<BaseEntity> bankAccountsList = new ArrayList<>();
        bankAccounts.forEach(bankAccountsList::add);
        return bankAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByIdQuery query) {
        var bankAccount = repository.findById(query.getId());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(bankAccount.get());
        return bankAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountByHolderQuery query) {
        var bankAccount = repository.findByAccountHolder(query.getAccountHolder());
        if (bankAccount.isEmpty()) {
            return null;
        }
        List<BaseEntity> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(bankAccount.get());
        return bankAccountsList;
    }

    @Override
    public List<BaseEntity> handle(FindAccountsWithBalanceQuery query) {
        return query.getEqualityType() == EqualityType.GREATER_THAN ? repository.findByBalanceGreaterThan(query.getBalance()) : repository.findByBalanceLessThan(query.getBalance());
    }
}
