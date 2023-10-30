package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.dedagroup.project_cea.dto.response.ApartmentDTOResponse;
import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.exception.model.UserNotFoundException;
import it.dedagroup.project_cea.mapper.*;
import it.dedagroup.project_cea.model.*;
import it.dedagroup.project_cea.service.def.*;
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

@Service
public class CustomerFacade {

	@Autowired
	CustomerServiceDef customerServiceDef;
	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	CustomerMapper CustomerMapper;
	@Autowired
	BillServiceDef billServiceDef;
	@Autowired
	ApartmentServiceDef apartmentServiceDef; 
	@Autowired
	InterventionServiceDef interventionServiceDef;
	@Autowired
	ScanServiceDef scanServiceDef;

	@Autowired
	InterventionMapper interventionMapper;
	@Autowired
	ApartmentMapper apartmentMapper;
	@Autowired
	BillMapper billMapper;
	@Autowired
	ScanMapper scanMapper;

	public void saveCustomer(AddCustomerDtoRequest request) {
		try {
			Customer c1 = customerServiceDef.findCustomerByUsernameAndIsAvailableTrue(request.getUsername());
			if (c1 != null)throw new NotValidDataException("This username is not available: "+request.getUsername());
		} catch (UserNotFoundException e) {
			try{
				Customer c2 = customerServiceDef.findCustomerByTaxCodeAndIsAvailableTrue(request.getTaxCode());
				if (c2.getTaxCode().equals(request.getTaxCode()))
					throw new NotValidDataException("Already in use this tax code: "+request.getTaxCode());
			} catch(UserNotFoundException a) {
				//TODO Da discutere l'aggiunta dell'appartamento in customer
				//Apartment a = apartmentServiceDef.findById(request.getId_apartment());
				Customer customerAdd = customerMapper.fromAddCustomerDTORequestToCustomer(request);
				customerServiceDef.saveCustomer(customerAdd);
			}
		}
	}

	public void modifyCustomer(EditCustomerDto request) {
		Customer customer = customerServiceDef.findCustomerByApartments_IdAndIsAvailableTrue(request.getId());
		if (!customer.getName().equals(request.getName())) {
			customer.setName(request.getName());
		}
		if (!customer.getSurname().equals(request.getSurname())) {
			customer.setSurname(request.getSurname());
		}
		if (customerServiceDef.findCustomerByUsernameAndIsAvailableTrue(request.getUsername()) != null) {
			throw new NotValidDataException("Existing username!");
		} else if (!customer.getUsername().equals(request.getUsername())) {
			customer.setUsername(request.getUsername());
		}
		if (!customer.getPassword().equals(request.getOldPassword())) {
			throw new NotValidDataException("Wrong password!");
		} else if (!request.getNewPassword().equals(request.getRepeatNewPassword())) {
			throw new NotValidDataException("Repeated password doesn't match!");
		} else {
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

	public void bookIntervention(BookInterventionDto request) {
		LocalDate interventionDate = LocalDate.parse(request.getInterventionDate());
		List<Intervention> interventions = interventionServiceDef.findAllByApartment_Customer_Id(request.getIdCustomer());
		boolean interventionRequest = interventions.stream()
				.anyMatch(i -> i.getInterventionDate().equals(interventionDate)
						&& i.getType().equals(TypeOfIntervention.FIXING_UP));
		if (interventionRequest)throw new NotValidDataException("Already exists an intervention request with this date");
		//TODO Sostituire la creazione di un intervento con il mapper
		Intervention intervention= interventionMapper.fromBookInterventionDTORequestToIntervention(request);
		interventionServiceDef.save(intervention);
	}

	public List<BillDTOResponse> getBills(long id_customer) {
		if (id_customer < 1)
			throw new NotValidDataException("Error insert a valid customer id: " + id_customer);
		return billMapper.toBillDTOResponseList(billServiceDef.findAllBillByScan_Apartment_Customer_Id(id_customer));
	}
 
	public BillDTOResponse payBill(PayBillDto request) {
		Customer c = customerServiceDef.findCustomerByApartments_IdAndIsAvailableTrue(request.getIdCustomer());
		LocalDate paymentDate = LocalDate.parse(request.getPaymentDate());
		List<Bill> billList = billServiceDef.findAllBillByScan_Apartment_Customer_Id(request.getIdCustomer());
		boolean billPayed = billList.stream()
				.anyMatch(b -> b.getId() == request.getIdBill()
						&& b.getScan().getApartment().getCustomer().getId() == c.getId()
						&& b.getPaymentDay()==null
				);
		if (!billPayed)throw new NotValidDataException("Bill already payed");
		return billMapper.toBillDTOResponse(customerServiceDef.payBill(request.getIdBill(), paymentDate));
	}

	public ScanDTOResponse autoScan(MeterScanDto request) {
		LocalDate scanDate = LocalDate.parse(request.getScanDate());
		List<Scan> scans = scanServiceDef.findAllScanByApartmentId(request.getIdApartment());
		boolean scanMade = scans.stream()
				.anyMatch(
						s -> s.getApartment().getId() == request.getIdApartment() &&
								s.getScanDate().equals(scanDate)
								&& s.getMcLiter() == request.getMcLiter()
				);
		if (scanMade)throw new NotValidDataException("Scan already saved");
		Scan scan = scanMapper.fromScanToDTORequest(request);
		return scanMapper.toScanDTOResponse(customerServiceDef.autoScan(scan));
	}
	
	public CustomerDtoResponse findCustomerById(long id_customer) {
		if (id_customer < 1)
			throw new NotValidDataException("Error insert a valid customer id: " + id_customer);
		return customerMapper.toDto(customerServiceDef.findCustomerByApartments_IdAndIsAvailableTrue(id_customer));
	}

	public List<CustomerDtoResponse> findAllCustomer() {
		return customerMapper.toListDto(customerServiceDef.findAllAndIsAvailableTrue());
	}
	
	public CustomerDtoResponse findCustomerByUsernameAndPassword(LoginDTORequest request) {
		return customerMapper.toDto(
				customerServiceDef.findCustomerByUsernameAndPasswordAndIsAvailableTrue(request.getUsername(), request.getPassword()));
	}

	public CustomerDtoResponse findCustomerByUsername(String username) {
		if (username == null || username.trim().isEmpty()) {
			throw new NotValidDataException("Error insert a valid username");
		}
		return CustomerMapper.toDto(customerServiceDef.findCustomerByUsernameAndIsAvailableTrue(username));
	}

	public CustomerDtoResponse findCustomerByTax_Code(String taxCode) {
		if (taxCode == null || taxCode.isEmpty())throw new NotValidDataException("Error insert a valid tax code");
		return CustomerMapper.toDto(customerServiceDef.findCustomerByTaxCodeAndIsAvailableTrue(taxCode));
	}

	public List<CustomerDtoResponse> findAllCustomerByNameAndSurname(CustomerNameSurnameDtoRequest request) {
		return customerMapper.toListDto(customerServiceDef.findAllCustomerByNameAndSurnameAndIsAvailableTrue(request.getName(), request.getSurname()));
	}

	public CustomerDtoResponse findCustomerByApartments_Id(long id_apartment) {
		if (id_apartment < 1)
			throw new NotValidDataException("Error insert a valid apartment id: " + id_apartment);
		return customerMapper.toDto(customerServiceDef.findCustomerByApartments_IdAndIsAvailableTrue(id_apartment));
	}

	public List<ApartmentDTOResponse> findAllApartmentByCustomerId(long id_customer) {
		if (id_customer < 1)
			throw new NotValidDataException("Error insert a valid apartment id: " + id_customer);
		return apartmentMapper.toListDto(apartmentServiceDef.findAllApartmentByCustomerIdAndIsAvailableTrue(id_customer));
	}
}
