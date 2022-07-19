package com.leonardovee.account.cmd.api.commands;

import com.leonardovee.account.common.dto.AccountType;
import com.leonardovee.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class OpenAccountCommand extends BaseCommand {
    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;
}
