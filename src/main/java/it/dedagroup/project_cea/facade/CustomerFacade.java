package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.response.CustomerDtoResponse;
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
	public CustomerDtoResponse saveCustomer(Customer customer) {
		return null;
	}
	public CustomerDtoResponse modifyCustomer(Customer customer){
		return null;
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
	
	public CustomerDtoResponse findCustomerById(long customer_id){
		return null;
	}
	public List<CustomerDtoResponse> findAllCustomer(){
		return null;
	}
	public CustomerDtoResponse findCustomerByUsernameAndPassword(String username, String password){
		return null;
	}
	public CustomerDtoResponse findCustomerByUsername(String username){
		return null;
	}
	public CustomerDtoResponse findCustomerByTax_Code(String taxCode){
		return null;
	}
	public List<CustomerDtoResponse> findAllCustomerByNameAndSurname(String name, String surname){
		return null;
	}
	public CustomerDtoResponse findCustomerByApartments_Id(long apartment_id){
		return null;
	}
}
