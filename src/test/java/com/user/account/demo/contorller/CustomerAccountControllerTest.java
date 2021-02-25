package com.user.account.demo.contorller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCustomer()
            throws Exception {

        mvc.perform(get("/api/customers/10001")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is("Sam Hat")))
                .andExpect(jsonPath("$.customerId", is(10001)))
        ;
    }

    @Test
    public void getCustomer_nullCustomerId()
            throws Exception {

        mvc.perform(get("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void getCustomer_resourceNotFound()
            throws Exception {

        mvc.perform(get("/api/customers/10002")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void getCustomerAccounts()
            throws Exception {
        /*[
                {
                    "id": 1,
                    "balanceData": 1500,
                    "openingAvailableBalance": 1200,
                    "accountType": {
                        "id": 1,
                        "accountName": "SGSavings726",
                        "accountType": "Savings",
                        "currency": "AUD"
                    }
                }
        ]*/
        mvc.perform(get("/api/customers/10001/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].balanceData", is(1500.00)))
                .andExpect(jsonPath("$.[0].openingAvailableBalance", is(1200.00)))
                .andExpect(jsonPath("$.[0].openingAvailableBalance", is(1200.00)))
                .andExpect(jsonPath("$.[0].accountType.accountName", is("SGSavings726")))
                .andExpect(jsonPath("$.[0].accountType.accountType", is("Savings")))
                .andExpect(jsonPath("$.[0].accountType.currency", is("AUD")))
        ;
    }

    @Test
    public void getCustomerAccounts_emptyCustomerId()
            throws Exception {
        mvc.perform(get("/api/customers//accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void getCustomerAccounts_resourceNotFound()
            throws Exception {
        mvc.perform(get("/api/customers/2/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void getCustomerAccountTransactions()
            throws Exception {
        /*[
               {
                  "id": 1,
                  "account": {
                     "id": 1,
                     "accountNumber": "1000001",
                     "balanceData": 1500,
                     "openingAvailableBalance": 1200,
                     "accountType": {
                        "id": 1,
                        "accountName": "SGSavings726",
                        "accountType": "Savings",
                        "currency": "AUD"
                     }
                  },
                  "creditDebit": true,
                  "amount": 50,
                  "transactionDate": "2021-02-25T22:51:34.901"
               },
               .....
        ]*/
        mvc.perform(get("/api/customers/10001/accounts/1/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].creditDebit", is(true)))
                .andExpect(jsonPath("$.[0].amount", is(50.0)))
                .andExpect(jsonPath("$.[0].transactionDate", is(Matchers.any(String.class))))
                .andExpect(jsonPath("$.[0].account.accountNumber", is("1000001")))
                .andExpect(jsonPath("$.[0].account.balanceData", is(1500.0)))
                .andExpect(jsonPath("$.[0].account.openingAvailableBalance", is(1200.0)))
                .andExpect(jsonPath("$.[0].account.accountType.accountName", is("SGSavings726")))
                .andExpect(jsonPath("$.[0].account.accountType.accountType", is("Savings")))
                .andExpect(jsonPath("$.[0].account.accountType.currency", is("AUD")))
        ;
    }

    @Test
    public void getCustomerAccountTransactions_emptyIds()
            throws Exception {
        mvc.perform(get("/api/customers/adsf/accounts/asdf/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    public void getCustomerAccountTransactions_resourceNotFound()
            throws Exception {
        mvc.perform(get("/api/customers/2/accounts/2/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
        ;
    }
}
