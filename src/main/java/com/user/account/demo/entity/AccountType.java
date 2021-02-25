package com.user.account.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AccountType {
    @Id
    @GeneratedValue
    private Long id;

    private String accountName;
    private String accountType;
    private String currency;
}
