package it.dedagroup.project_cea.service.def;

import java.util.List;
import java.util.Optional;

import it.dedagroup.project_cea.model.Customer;

public interface CustomerServiceDef {
	public Customer saveCustomer(Customer customer);
	public Customer modifyCustomer(Customer customer);
	public void deleteCustomer(long customer_id);
	
	public Customer findCustomerByUsernameAndPassword(String username, String password);
	public Optional<Customer> findCustomerByUsername(String username);
	public Customer findCustomerByTax_Code(String taxCode);
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname);
	public Customer findCustomerByCellphone(String cellphone);
	public Customer findCustomerByApartments_Id(long apartment_id);
	
}
