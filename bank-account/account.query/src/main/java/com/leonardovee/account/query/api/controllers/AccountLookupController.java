package com.leonardovee.account.query.api.controllers;

import com.leonardovee.account.query.api.dto.AccountLookupResponse;
import com.leonardovee.account.query.api.dto.EqualityType;
import com.leonardovee.account.query.api.queries.FindAccountByHolderQuery;
import com.leonardovee.account.query.api.queries.FindAccountByIdQuery;
import com.leonardovee.account.query.api.queries.FindAccountsWithBalanceQuery;
import com.leonardovee.account.query.api.queries.FindAllAccountsQuery;
import com.leonardovee.account.query.domain.BankAccount;
import com.leonardovee.cqrs.core.infrastructure.QueryDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/api/v1/bank-account-lookup")
public class AccountLookupController {
    private final Logger logger = Logger.getLogger(AccountLookupController.class.getName());

    @Autowired
    private QueryDispatcher queryDispatcher;

    @GetMapping(path = "/")
    public ResponseEntity<AccountLookupResponse> getAllAccounts() {
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAllAccountsQuery());
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response = AccountLookupResponse.builder().accounts(accounts).message(MessageFormat.format("Successfully returned {0} bank account(s).", accounts.size())).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get all accounts request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/by-id/{id}")
    public ResponseEntity<AccountLookupResponse> getAccountsById(@PathVariable(value = "id") String id) {
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByIdQuery(id));
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response = AccountLookupResponse.builder().accounts(accounts).message("Successfully returned bank account.").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by id request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/by-holder/{holder}")
    public ResponseEntity<AccountLookupResponse> getAccountByHolder(@PathVariable(value = "holder") String holder) {
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountByHolderQuery(holder));
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response = AccountLookupResponse.builder().accounts(accounts).message("Successfully returned bank account.").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get account by holder request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/with-balance/{equality}/{balance}")
    public ResponseEntity<AccountLookupResponse> getAccountsWithBalance(@PathVariable(value = "equality") EqualityType equality, @PathVariable(value = "balance") double balance) {
        try {
            List<BankAccount> accounts = queryDispatcher.send(new FindAccountsWithBalanceQuery(equality, balance));
            if (accounts == null || accounts.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            var response = AccountLookupResponse.builder().accounts(accounts).message(MessageFormat.format("Successfully returned {0} bank account(s).", accounts.size())).build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete get accounts with balance request!";
            logger.log(Level.SEVERE, safeErrorMessage, e);
            return new ResponseEntity<>(new AccountLookupResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
