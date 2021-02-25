package com.user.account.demo.repository;

import com.user.account.demo.entity.Transaction;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByAccount_Customer_customerIdAndAccount_id(Long customerId, Long accountId);
}
