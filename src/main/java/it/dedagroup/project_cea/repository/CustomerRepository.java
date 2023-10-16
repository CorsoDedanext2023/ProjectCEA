package it.dedagroup.project_cea.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
