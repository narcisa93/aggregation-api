package com.aggregation.api.repository;

import com.aggregation.api.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    List<Account> findByUserId(Long userId);

}
