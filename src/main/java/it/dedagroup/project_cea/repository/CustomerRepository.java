package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findCustomerByUsernameAndPassword(String username, String password);
	public Optional<Customer> findCustomerByUsername(String username);
	public Optional<Customer> findCustomerByTaxCode(String taxCode);
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname);
	public Optional<Customer> findCustomerByApartments_Id(long apartment_id);


}
