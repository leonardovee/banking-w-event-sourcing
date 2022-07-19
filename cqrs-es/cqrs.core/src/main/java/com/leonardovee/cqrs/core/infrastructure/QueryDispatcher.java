package com.leonardovee.cqrs.core.infrastructure;

import com.leonardovee.cqrs.core.domain.BaseEntity;
import com.leonardovee.cqrs.core.queries.BaseQuery;
import com.leonardovee.cqrs.core.queries.QueryHandlerMethod;

import java.util.List;

public interface QueryDispatcher {
    <T extends BaseQuery> void registerHandler(Class<T> type, QueryHandlerMethod<T> handler);

    <U extends BaseEntity> List<U> send(BaseQuery query);
}
