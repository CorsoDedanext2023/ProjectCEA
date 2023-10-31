package it.dedagroup.project_cea.service.def;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;

public interface CustomerServiceDef {
	public void saveCustomer(Customer customer);
	public void modifyCustomer(Customer customer);
	public void deleteCustomer(long customer_id);
	public Bill payBill(long id_bill, LocalDate paymentDate);
	public Scan autoScan(Scan scan);

	public Customer findCustomerByIdAndIsAvailableTrue(long id_customer);
	public List<Customer> findAllAndIsAvailableTrue();
	public Customer findCustomerById(long id_customer);
	public List<Customer> findAllCustomer();
	public Customer findCustomerByUsernameAndPassword(String username, String password);
	public Customer findCustomerByUsername(String username);
	public Customer findCustomerByTaxCode(String taxCode);
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname);
	public Customer findCustomerByApartments_Id(long id_apartment);

	public Customer findCustomerByUsernameAndPasswordAndIsAvailableTrue(String username, String password);
	public Customer findCustomerByUsernameAndIsAvailableTrue(String username);
	public Customer findCustomerByTaxCodeAndIsAvailableTrue(String taxCode);
	public List<Customer> findAllCustomerByNameAndSurnameAndIsAvailableTrue(String name, String surname);
	public Customer findCustomerByApartments_IdAndIsAvailableTrue(long apartment_id);

    Customer findByIdAndIsAvailableTrue(long idCustomer);
}

//metodi del Customer vuoti, li definiamo nell implementation