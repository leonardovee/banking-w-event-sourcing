package com.leonardovee.account.cmd.api.commands;

import com.leonardovee.cqrs.core.commands.BaseCommand;
import lombok.Data;

@Data
public class WithdrawFundsCommand extends BaseCommand {
    private double amount;
}
