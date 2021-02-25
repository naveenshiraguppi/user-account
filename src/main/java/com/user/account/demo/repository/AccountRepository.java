package com.user.account.demo.repository;

import com.user.account.demo.entity.Account;
import com.user.account.demo.entity.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByCustomer_customerId(Long customerId);
}
