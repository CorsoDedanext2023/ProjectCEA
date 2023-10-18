package it.dedagroup.project_cea.service.def;

import java.time.LocalDate;
import java.util.List;

import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;

public interface CustomerServiceDef {
	public Customer saveCustomer(Customer customer);
	public Customer modifyCustomer(Customer customer);
	public void deleteCustomer(long customer_id);
	
	public Intervention bookIntervention(long user_id, long apartment_id, LocalDate interventionDate);
	public List<Bill> getBills(long user_id);
	public Bill payBill(long bill_id, LocalDate paymentDate);
	public Scan meterScan(long apartment_id, double mcLiter);
	
	public Customer findCustomerById(long customer_id);
	public List<Customer> findAllCustomer();
	public Customer findCustomerByUsernameAndPassword(String username, String password);
	public Customer findCustomerByUsername(String username);
	public Customer findCustomerByTaxCode(String taxCode);
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname);
	public Customer findCustomerByApartments_Id(long apartment_id);
	
}
