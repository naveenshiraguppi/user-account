package com.user.account.demo.service;

import com.user.account.demo.controller.ResourceNotFoundException;
import com.user.account.demo.entity.Account;
import com.user.account.demo.entity.Customer;
import com.user.account.demo.entity.Transaction;
import com.user.account.demo.repository.AccountRepository;
import com.user.account.demo.repository.AccountTransactionRepository;
import com.user.account.demo.repository.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerAccountServiceTest  {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private AccountTransactionRepository accountTransactionRepository;
    private Customer customer;
    private List<Account> accounts;
    private List<Transaction> transactions;
    private Account account;
    private Transaction transaction;

    private CustomerAccountService customerAccountService;

    @Before
    public void setup() {
        customerRepository = Mockito.mock(CustomerRepository.class);
        customer = mock(Customer.class);
        when(customerRepository.findByCustomerId(any(Long.class))).thenReturn(customer);

        accountRepository = Mockito.mock(AccountRepository.class);
        accounts = new ArrayList<>();
        account = mock(Account.class);
        accounts.add(account);
        when(accountRepository.findByCustomer_customerId(any(Long.class))).thenReturn(accounts);

        accountTransactionRepository = Mockito.mock(AccountTransactionRepository.class);
        transactions = new ArrayList<>();
        transaction = mock(Transaction.class);
        transactions.add(transaction);
        when(accountTransactionRepository.findByAccount_Customer_customerIdAndAccount_id(any(Long.class), any(Long.class))).thenReturn(transactions);

        customerAccountService = new CustomerAccountService(customerRepository, accountRepository, accountTransactionRepository);
    }

    @Test
    public void getCustomer() {
        customerAccountService.getCustomer(10001L);
        verify(customerRepository, atLeastOnce()).findByCustomerId(eq(10001L));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getCustomer_ResourceNotFoundException() {
        when(customerRepository.findByCustomerId(any(Long.class))).thenReturn(null);
        customerAccountService.getCustomer(10002L);
    }

    @Test
    public void getCustomerAccounts() {
        customerAccountService.getCustomerAccounts(10001L);
        verify(accountRepository, atLeastOnce()).findByCustomer_customerId(eq(10001L));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getCustomerAccounts_ResourceNotFoundException() {
        accounts.clear();
        customerAccountService.getCustomerAccounts(10002L);
    }

    @Test
    public void getCustomerAccountTransactions() {
        customerAccountService.getCustomerAccountTransactions(10001L, 1L);
        verify(accountTransactionRepository, atLeastOnce()).findByAccount_Customer_customerIdAndAccount_id(eq(10001L), eq(1L));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getCustomerAccountTransactions_ResourceNotFoundException() {
        transactions.clear();
        customerAccountService.getCustomerAccountTransactions(10001L, 1L);
    }
}
