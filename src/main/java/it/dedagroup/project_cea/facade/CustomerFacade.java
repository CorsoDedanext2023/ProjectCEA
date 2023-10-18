package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.AddCustomerDto;
import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.mapper.CustomerMapper;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;
@Service
public class CustomerFacade {
	@Autowired
	CustomerServiceDef customerServiceDef;
	//TODO CustomerFacade da implementare + controlli
	@Autowired
	CustomerMapper CustomerMapper;
	public void saveCustomer(AddCustomerDto request) {
		try {
			customerServiceDef.findCustomerByUsername(request.getUsername());
		} catch (NotValidDataException e) {
			Customer customerAdd = new Customer();
			customerAdd.setName(request.getName());
			customerAdd.setSurname(request.getSurname());
			customerAdd.setUsername(request.getSurname());
			customerAdd.setPassword(request.getPassword());
			customerAdd.setTaxCode(request.getTaxCode());
			customerServiceDef.saveCustomer(customerAdd);
		}
		throw new NotValidDataException("Error in insert a new customer");
	}
	public void modifyCustomer(Customer customer){
	}
	
	public void deleteCustomer(long customer_id){

	}
	
	public Intervention bookIntervention(long user_id, LocalDate interventionDate) {
		return null;
	}

	public List<Bill> getBills(long user_id) {
		return null;
	}

	public Bill payBill(long bill_id, LocalDate paymentDate) {
		return null;
	}

	public Scan meterScan(long apartment_id, Bill lastBill) {
		return null;
	}
	
	public CustomerDto findCustomerById(long customer_id){
		return null;
	}
	public List<CustomerDto> findAllCustomer(){
		return null;
	}
	public CustomerDto findCustomerByUsernameAndPassword(String username, String password){
		return null;
	}
	public CustomerDto findCustomerByUsername(String username){
		String regexUsername = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		if (username==null || username.trim().isEmpty() || !username.matches(regexUsername)) {
			throw new NotValidDataException("Error, insert a valid tax code");
		}
		return CustomerMapper.toDto(customerServiceDef.findCustomerByUsername(regexUsername));
	}
	public CustomerDto findCustomerByTax_Code(String taxCode){
		String regexParametri = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";
		if (!taxCode.matches(regexParametri)) {
			throw new NotValidDataException("Error, insert a valid tax code");
		}
		return CustomerMapper.toDto(customerServiceDef.findCustomerByTaxCode(taxCode));
	}
	public List<CustomerDto> findAllCustomerByNameAndSurname(String name, String surname){
		return null;
	}
	public CustomerDto findCustomerByApartments_Id(long apartment_id){
		return null;
	}
}
