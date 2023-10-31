package it.dedagroup.project_cea.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.dedagroup.project_cea.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public Optional<Customer> findCustomerByIdAndIsAvailableTrue(long id_customer);
	public Optional<Customer> findCustomerByUsernameAndPasswordAndIsAvailableTrue(String username, String password);
	public Optional<Customer> findCustomerByUsernameAndPassword(String username, String password);
	public Optional<Customer> findCustomerByUsernameAndIsAvailableTrue(String username);
	public Optional<Customer> findCustomerByUsername(String username);
	public Optional<Customer> findCustomerByTaxCodeAndIsAvailableTrue(String taxCode);
	public Optional<Customer> findCustomerByTaxCode(String taxCode);
	public List<Customer> findAllCustomerByNameAndSurnameAndIsAvailableTrue(String name, String surname);
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname);
	public Optional<Customer> findCustomerByApartments_IdAndIsAvailableTrue(long apartment_id);
	public Optional<Customer> findCustomerByApartments_Id(long apartment_id);
	public Optional<Customer> findByIdAndIsAvailableTrue(long idCustomer);

}

//classe che interagisce con il db, facciamo una CRUD personalizzata...questa interfaccia deve essere istanziata nell implementation