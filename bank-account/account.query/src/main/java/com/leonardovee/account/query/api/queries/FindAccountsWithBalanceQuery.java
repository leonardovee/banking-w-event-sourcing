package com.leonardovee.account.query.api.queries;

import com.leonardovee.account.query.api.dto.EqualityType;
import com.leonardovee.cqrs.core.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccountsWithBalanceQuery extends BaseQuery {
    private EqualityType equalityType;
    private double balance;
}
