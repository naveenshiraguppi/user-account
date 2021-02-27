package com.user.account.demo.service;

import com.user.account.demo.controller.ResourceNotFoundException;
import com.user.account.demo.entity.Account;
import com.user.account.demo.entity.Transaction;
import com.user.account.demo.entity.Customer;
import com.user.account.demo.repository.AccountRepository;
import com.user.account.demo.repository.AccountTransactionRepository;
import com.user.account.demo.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomerAccountService {
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private AccountTransactionRepository accountTransactionRepository;

    @Autowired
    public CustomerAccountService(CustomerRepository customerRepository, AccountRepository accountRepository,
                                  AccountTransactionRepository accountTransactionRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    public Customer getCustomer(Long customerId) {
        log.info("getCustomer {}", customerId);
        Customer customer = customerRepository.findByCustomerId(customerId);
        checkResource(customer, customerId);
        return customer;
    }

    private void checkResource(Object resource, Long customerId) {
        if(resource == null) {
            throwResourceNotFoundException(customerId);
        }
    }

    private void throwResourceNotFoundException(Long id) {
        throw new ResourceNotFoundException("Resource not found of id " + id);
    }

    public List<Account> getCustomerAccounts(Long customerId) {
        log.info("getCustomerAccounts {}", customerId);
        List<Account> accounts = new ArrayList<>(accountRepository.findByCustomer_customerId(customerId));
        if(accounts.isEmpty()) {
            throwResourceNotFoundException(customerId);
        }
        return accounts;
    }

    public List<Transaction> getCustomerAccountTransactions(Long customerId, Long accountId) {
        log.info("getCustomerAccountTransactions customerId {} accountId {}", customerId, accountId);
        List<Transaction> transactions = new ArrayList<>(accountTransactionRepository.findByAccount_Customer_customerIdAndAccount_id(customerId, accountId));
        if(transactions.isEmpty()) {
            throwResourceNotFoundException(accountId);
        }
        return transactions;
    }
}
