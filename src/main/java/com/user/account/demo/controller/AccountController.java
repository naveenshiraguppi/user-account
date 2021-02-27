package com.user.account.demo.controller;

import com.user.account.demo.entity.Account;
import com.user.account.demo.entity.Transaction;
import com.user.account.demo.entity.Customer;
import com.user.account.demo.service.CustomerAccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    private CustomerAccountService customerAccountService;

    @Autowired
    public AccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") Long customerId) {
        return  customerAccountService.getCustomer(customerId);
    }

    @GetMapping("/customers/{customerId}/accounts")
    public List<Account> getCustomerAccounts(@PathVariable(value = "customerId") Long customerId) {
        return customerAccountService.getCustomerAccounts(customerId);
    }

    @GetMapping("/customers/{customerId}/accounts/{accountId}/transactions")
    public List<Transaction> getCustomerAccounts(@PathVariable("customerId") Long customerId, @PathVariable("accountId") Long accountId) {
        return customerAccountService.getCustomerAccountTransactions(customerId, accountId);
    }

}
