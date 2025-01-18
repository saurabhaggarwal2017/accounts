package com.eazybytes.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account extends BaseEntity {
    @Id
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Long customerId;

}
