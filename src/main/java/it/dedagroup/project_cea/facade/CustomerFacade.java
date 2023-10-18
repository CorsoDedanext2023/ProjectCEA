package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.AddCustomerDto;
import it.dedagroup.project_cea.dto.request.BookInterventionDto;
import it.dedagroup.project_cea.dto.request.EditCustomerDto;
import it.dedagroup.project_cea.dto.request.MeterScanDto;
import it.dedagroup.project_cea.dto.request.PayBillDto;
import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
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
	public void modifyCustomer(EditCustomerDto request){
		try {
			Customer customer=customerServiceDef.findCustomerById(request.getId());
			if(!customer.getName().equals(request.getName())) {
				customer.setName(request.getName());
			}
			if(!customer.getSurname().equals(request.getSurname())) {
				customer.setSurname(request.getSurname());
			}
			if(customerServiceDef.findCustomerByUsername(request.getUsername())!=null) {
				throw new NotValidDataException("Existing username!");
			}else if(!customer.getUsername().equals(request.getUsername())) {
				customer.setUsername(request.getUsername());
			}
			if(!customer.getPassword().equals(request.getOldPassword())) {
				throw new NotValidDataException("Wrong password!");
			}else if(!request.getNewPassword().equals(request.getRepeatNewPassword())) {
				throw new NotValidDataException("Repeated password doesn't match!");
			}else if(request.getNewPassword().equals(request.getRepeatNewPassword())&&
					request.getNewPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
				customer.setPassword(request.getNewPassword());
			}
			if(!customer.getTaxCode().equals(request.getTaxCode()) &&
					customer.getTaxCode().matches("[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]")) {
				customer.setTaxCode(request.getTaxCode());
			}
			customerServiceDef.modifyCustomer(customer);
		}catch(NotValidDataException e) {
			e.getMessage();
		}
	}
	
	public void deleteCustomer(long id_customer){
		customerServiceDef.deleteCustomer(id_customer);
	}
	
	public Intervention bookIntervention(BookInterventionDto request) {
		return customerServiceDef.bookIntervention(request.getIdCustomer(), request.getIdApartment(), request.getInterventionDate());
	}

	public List<Bill> getBills(long id_user) {
		return null;
	}

	public Bill payBill(PayBillDto request) {
		return customerServiceDef.payBill(request.getIdBill(), request.getPaymentDate());
	}

	public Scan meterScan(MeterScanDto request) {
		return customerServiceDef.meterScan(request.getIdApartment(), request.getMcLiter());
	}
	
	public CustomerDto findCustomerById(long id_customer){
		return null;
	}
	public List<CustomerDto> findAllCustomer(){
		return null;
	}
	public CustomerDto findCustomerByUsernameAndPassword(String username, String password){
		return null;
	}
	public CustomerDto findCustomerByUsername(String username){
		return null;
	}
	public CustomerDto findCustomerByTax_Code(String taxCode){
		return null;
	}
	public List<CustomerDto> findAllCustomerByNameAndSurname(String name, String surname){
		return null;
	}
	public CustomerDto findCustomerByApartments_Id(long apartment_id){
		return null;
	}
}
