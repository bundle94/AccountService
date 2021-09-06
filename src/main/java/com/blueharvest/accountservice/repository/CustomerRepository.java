package com.blueharvest.accountservice.repository;

import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /*@Query("SELECT name, surname FROM  customer a JOIN cache_media b on a.id=b.id")
    public List<UserDetails> fetchUserDetailsById(long id);*/
}
