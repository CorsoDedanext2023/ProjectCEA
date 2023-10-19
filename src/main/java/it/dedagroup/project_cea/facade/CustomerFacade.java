package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.AddCustomerDto;
import it.dedagroup.project_cea.dto.request.BookInterventionDto;
import it.dedagroup.project_cea.dto.request.EditCustomerDto;
import it.dedagroup.project_cea.dto.request.LoginDTORequest;
import it.dedagroup.project_cea.dto.request.MeterScanDto;
import it.dedagroup.project_cea.dto.request.NameSurnameRequest;
import it.dedagroup.project_cea.dto.request.PayBillDto;
import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.mapper.CustomerMapper;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Customer;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.service.def.CustomerServiceDef;
import jakarta.validation.constraints.Pattern;

@Service
public class CustomerFacade {
	@Autowired
	CustomerServiceDef customerServiceDef;
	@Autowired
	CustomerMapper customerMapper;
	// TODO CustomerFacade da implementare + controlli
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
		throw new NotValidDataException("Error existing user with username: " + request.getUsername());
	}

	public void modifyCustomer(EditCustomerDto request) {
		try {
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
			} else if (request.getNewPassword().equals(request.getRepeatNewPassword()) && request.getNewPassword()
					.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
				customer.setPassword(request.getNewPassword());
			}
			if (!customer.getTaxCode().equals(request.getTaxCode())
					&& customer.getTaxCode().matches("[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]")) {
				customer.setTaxCode(request.getTaxCode());
			}
			customerServiceDef.modifyCustomer(customer);
		} catch (NotValidDataException e) {
			e.getMessage();
		}
	}

	public void deleteCustomer(long id_customer) {
		customerServiceDef.deleteCustomer(id_customer);
	}

	public Intervention bookIntervention(BookInterventionDto request) {
		return customerServiceDef.bookIntervention(request.getIdCustomer(), request.getIdApartment(),
				request.getInterventionDate());
	}

	public List<Bill> getBills(long id_user) {
		return null;
	}

	public Bill payBill(PayBillDto request) {
		return customerServiceDef.payBill(request.getIdBill(), request.getPaymentDate());
	}

	public Scan autoScan(MeterScanDto request) {
		return customerServiceDef.autoScan(request.getIdApartment(), request.getMcLiter());
	}

	public CustomerDto findCustomerById(long id_customer) {
		if (id_customer < 0)
			throw new NotValidDataException("Error insert a valid customer id: " + id_customer);
		return customerMapper.toDto(customerServiceDef.findCustomerById(id_customer));
	}

	public List<CustomerDto> findAllCustomer() {
		return customerMapper.toListDto(customerServiceDef.findAllCustomer());
	}

	public CustomerDto findCustomerByUsernameAndPassword(LoginDTORequest request) {
		return customerMapper.toDto(customerServiceDef.findCustomerByUsernameAndPassword(request.getUsername(), request.getPassword()));
	}

	public CustomerDto findCustomerByUsername(String username) {
		String regexUsername = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		if (username == null || username.trim().isEmpty() || !username.matches(regexUsername)) {
			throw new NotValidDataException("Error, insert a valid tax code");
		}
		return CustomerMapper.toDto(customerServiceDef.findCustomerByUsername(regexUsername));
	}

	public CustomerDto findCustomerByTax_Code(String taxCode) {
		String regexParametri = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";
		if (!taxCode.matches(regexParametri)) {
			throw new NotValidDataException("Error, insert a valid tax code");
		}
		return CustomerMapper.toDto(customerServiceDef.findCustomerByTaxCode(taxCode));
	}

	public List<CustomerDto> findAllCustomerByNameAndSurname(NameSurnameRequest request) {
		return customerMapper.toListDto(customerServiceDef.findAllCustomerByNameAndSurname(request.getName(), request.getSurname()));
	}

	public CustomerDto findCustomerByApartments_Id(long id_apartment) {
		if (id_apartment < 0)
			throw new NotValidDataException("Error insert a valid apartment id: " + id_apartment);
		return customerMapper.toDto(customerServiceDef.findCustomerByApartments_Id(id_apartment));
	}
}
