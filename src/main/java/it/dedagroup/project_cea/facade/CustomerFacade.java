package it.dedagroup.project_cea.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.AddCustomerDtoRequest;
import it.dedagroup.project_cea.dto.request.BookInterventionDto;
import it.dedagroup.project_cea.dto.request.EditCustomerDto;
import it.dedagroup.project_cea.dto.request.LoginDTORequest;
import it.dedagroup.project_cea.dto.request.MeterScanDto;
import it.dedagroup.project_cea.dto.request.CustomerNameSurnameDtoRequest;
import it.dedagroup.project_cea.dto.request.PayBillDto;
import it.dedagroup.project_cea.dto.response.CustomerDtoResponse;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.mapper.CustomerMapper;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;
import it.dedagroup.project_cea.service.def.BillServiceDef;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;

@Service
public class CustomerFacade {

	@Autowired
	CustomerServiceDef customerServiceDef;
	@Autowired
	CustomerMapper customerMapper;
	// TODO CustomerFacade da implementare + controlli
	@Autowired
	CustomerMapper CustomerMapper;
	@Autowired
	BillServiceDef billServiceDef;
	@Autowired
	ApartmentServiceDef apartmentServiceDef; 
	
	public void saveCustomer(AddCustomerDtoRequest request) {
		try {
			Customer c1 = customerServiceDef.findCustomerByUsername(request.getUsername());
			if (c1 != null)throw new NotValidDataException("This username is not available: "+request.getUsername());
			Customer c2 = customerServiceDef.findCustomerByTaxCode(request.getTaxCode());
			if (c2 != null)throw new NotValidDataException("Already in use this tax code: "+request.getTaxCode());
		} catch (NotValidDataException e) {
			//TODO Da discutere l'aggiunta dell'appartamento in customer
			//Apartment a = apartmentServiceDef.findById(request.getId_apartment());
			Customer customerAdd = new Customer();
			//List<Apartment> apartments = new ArrayList<>();
			//apartments.add(a);
			customerAdd.setName(request.getName());
			customerAdd.setSurname(request.getSurname());
			customerAdd.setUsername(request.getUsername());
			customerAdd.setPassword(request.getPassword());
			customerAdd.setTaxCode(request.getTaxCode());
			//customerAdd.setApartments(apartments);
			customerServiceDef.saveCustomer(customerAdd);
		}
	}

	public void modifyCustomer(EditCustomerDto request) {
		Customer customer = customerServiceDef.findCustomerById(request.getId());
		if (!customer.getName().equals(request.getName())) {
			customer.setName(request.getName());
		}
		if (!customer.getSurname().equals(request.getSurname())) {
			customer.setSurname(request.getSurname());
		}
		if (customerServiceDef.findCustomerByUsername(request.getUsername()) != null) {
			throw new NotValidDataException("Existing username!");
		} else if (!customer.getUsername().equals(request.getUsername())) {
			customer.setUsername(request.getUsername());
		}
		if (!customer.getPassword().equals(request.getOldPassword())) {
			throw new NotValidDataException("Wrong password!");
		} else if (!request.getNewPassword().equals(request.getRepeatNewPassword())) {
			throw new NotValidDataException("Repeated password doesn't match!");
		} else if (request.getNewPassword().equals(request.getRepeatNewPassword())) {
			customer.setPassword(request.getNewPassword());
		}
		if (!customer.getTaxCode().equals(request.getTaxCode())) {
			customer.setTaxCode(request.getTaxCode());
		}
		customerServiceDef.modifyCustomer(customer);
	}

	public void deleteCustomer(long id_customer) {
		if (id_customer < 1)throw new NotValidDataException("Error insert a valid customer id: " + id_customer);
		customerServiceDef.deleteCustomer(id_customer);
	}

	public Intervention bookIntervention(BookInterventionDto request) {
		return customerServiceDef.bookIntervention(request.getIdCustomer(), request.getIdApartment(),
				request.getInterventionDate());
	}

	public List<Bill> getBills(long id_customer) {
		if (id_customer < 1)
			throw new NotValidDataException("Error insert a valid customer id: " + id_customer);
		return billServiceDef.findAllBillByScan_Apartment_Customer_Id(id_customer);
	}
 
	public Bill payBill(PayBillDto request) {
		return customerServiceDef.payBill(request.getIdBill(), request.getPaymentDate());
	}

	public Scan autoScan(MeterScanDto request) {
		return customerServiceDef.autoScan(request.getIdApartment(), request.getMcLiter());
	}
	
	public CustomerDtoResponse findCustomerById(long id_customer) {
		if (id_customer < 1)
			throw new NotValidDataException("Error insert a valid customer id: " + id_customer);
		return customerMapper.toDto(customerServiceDef.findCustomerById(id_customer));
	}

	public List<CustomerDtoResponse> findAllCustomer() {
		return customerMapper.toListDto(customerServiceDef.findAllCustomer());
	}
	
	public CustomerDtoResponse findCustomerByUsernameAndPassword(LoginDTORequest request) {
		return customerMapper.toDto(
				customerServiceDef.findCustomerByUsernameAndPassword(request.getUsername(), request.getPassword()));
	}

	public CustomerDtoResponse findCustomerByUsername(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new NotValidDataException("Error insert a valid username");
		}
		return CustomerMapper.toDto(customerServiceDef.findCustomerByUsername(username));
	}

	public CustomerDtoResponse findCustomerByTax_Code(String taxCode) {
		if (taxCode == null || taxCode.isEmpty())throw new NotValidDataException("Error insert a valid tax code");
		return CustomerMapper.toDto(customerServiceDef.findCustomerByTaxCode(taxCode));
	}

	public List<CustomerDtoResponse> findAllCustomerByNameAndSurname(CustomerNameSurnameDtoRequest request) {
		return customerMapper.toListDto(customerServiceDef.findAllCustomerByNameAndSurname(request.getName(), request.getSurname()));
	}

	public CustomerDtoResponse findCustomerByApartments_Id(long id_apartment) {
		if (id_apartment < 1)
			throw new NotValidDataException("Error insert a valid apartment id: " + id_apartment);
		return customerMapper.toDto(customerServiceDef.findCustomerByApartments_Id(id_apartment));
	}
}
