package it.dedagroup.project_cea.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.repository.CustomerRepository;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;

@Service
public class CustomerServiceImpl implements CustomerServiceDef{
	
	@Autowired
	CustomerRepository repo;
	
	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer modifyCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(long customer_id) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Intervention bookIntervention(long user_id, LocalDate interventionDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> getBills(long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bill payBill(long bill_id, LocalDate paymentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scan meterScan(long apartment_id, Bill lastBill) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer findCustomerById(long customer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByTaxCode(String taxCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAllCustomerByNameAndSurname(String name, String surname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findCustomerByApartments_Id(long apartment_id) {
		// TODO Auto-generated method stub
		return null;
	}
}
