package com.leonardovee.account.query.domain;

import com.leonardovee.account.common.dto.AccountType;
import com.leonardovee.cqrs.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BankAccount extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String accountHolder;
    private Date creationDate;
    private AccountType accountType;
    private double balance;

    public String getId() {
        return id;
    }
}
