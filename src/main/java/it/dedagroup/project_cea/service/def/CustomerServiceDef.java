package it.dedagroup.project_cea.service.def;

import java.util.List;

import it.dedagroup.project_cea.model.Customer;

public interface CustomerServiceDef {
	public Customer saveCustomer(Customer customer);
	public Customer modifyCustomer(Customer customer);
	public void deleteCustomer(long customer_id);
	
	public Customer findCustomerById(long customer_id);
	public Customer findAllCustomer();
	public Customer findCustomerByUsernameAndPassword(String username, String password);
	public Customer findCustomerByUsername(String username);
	public Customer findCustomerByTaxCode(String taxCode);
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname);
	public Customer findCustomerByApartments_Id(long apartment_id);
	
}
