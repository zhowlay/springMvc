package com.titan.myinterface;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.titan.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);
	//List<Customer> findByLastName(String lastName,Pageable pageable);
	//Page<Customer> findByLastname(String lastName, Pageable pageable);
	//Slice<Customer> findByLastname(String lastname, Pageable pageable);
	List<Customer> findByLastName(String lastname, Sort sort);

}
