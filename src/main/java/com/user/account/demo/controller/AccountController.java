package com.user.account.demo.controller;

import com.user.account.demo.entity.Account;
import com.user.account.demo.entity.Transaction;
import com.user.account.demo.entity.Customer;
import com.user.account.demo.service.CustomerAccountServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    private CustomerAccountServices customerAccountServices;

    @Autowired
    public AccountController(CustomerAccountServices customerAccountServices) {
        this.customerAccountServices = customerAccountServices;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long customerId) {
        return  customerAccountServices.getCustomer(customerId);
    }

    @GetMapping("/customers/{customerId}/accounts")
    public List<Account> getCustomerAccounts(@PathVariable(value = "customerId") Long customerId) {
        return customerAccountServices.getCustomerAccounts(customerId);
    }

    @GetMapping("/customers/{customerId}/accounts/{accountId}/transactions")
    public List<Transaction> getCustomerAccounts(@PathVariable("customerId") Long customerId, @PathVariable("accountId") Long accountId) {
        return customerAccountServices.getCustomerAccountTransactions(customerId, accountId);
    }

}
