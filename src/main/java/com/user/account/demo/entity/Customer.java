package com.user.account.demo.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 200)
    private String fullName;

    @Column
    private Long customerId;
}
