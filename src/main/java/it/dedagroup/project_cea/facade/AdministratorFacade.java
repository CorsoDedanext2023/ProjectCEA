package it.dedagroup.project_cea.facade;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.AceaBillRequest;
import it.dedagroup.project_cea.dto.request.AddApartmentDtoRequest;
import it.dedagroup.project_cea.dto.request.AddCondominiumDTORequest;
import it.dedagroup.project_cea.dto.request.AdministratorIdDtoRequest;
import it.dedagroup.project_cea.dto.request.AdministratorUpdateDTORequest;
import it.dedagroup.project_cea.dto.request.BillDTORequest;
import it.dedagroup.project_cea.dto.request.CondominiumDTORequest;
import it.dedagroup.project_cea.dto.request.RegisterUserDTORequest;
import it.dedagroup.project_cea.dto.response.AdministratorDtoResponse;
import it.dedagroup.project_cea.dto.response.ApartmentScanDTOResponse;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.dto.response.CustomerExtendedInfoDTOResponse;
import it.dedagroup.project_cea.mapper.AdministratorMapper;
import it.dedagroup.project_cea.mapper.ApartmentMapper;
import it.dedagroup.project_cea.mapper.BillMapper;
import it.dedagroup.project_cea.mapper.CondominiumMapper;
import it.dedagroup.project_cea.mapper.CustomerMapper;
import it.dedagroup.project_cea.mapper.ScanMapper;
import it.dedagroup.project_cea.model.Administrator;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.service.def.AdministratorServiceDef;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;
import it.dedagroup.project_cea.service.def.BillServiceDef;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;
import it.dedagroup.project_cea.service.def.ScanServiceDef;

@Service
public class AdministratorFacade {

	@Autowired
	AdministratorServiceDef service;
	@Autowired
	AdministratorMapper mapper;
	@Autowired
	CondominiumServiceDef condominiumService;
	@Autowired
	BillServiceDef billService;
	@Autowired
	BillMapper billMapper;
	@Autowired
	CondominiumMapper condominiumMapper;
	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	ApartmentServiceDef apartmentService;
	@Autowired
	ApartmentMapper apartmentMapper;
	@Autowired
	ScanServiceDef scanService;
	@Autowired
	ScanMapper scanMapper;
	@Autowired
	AdministratorServiceDef administratorService;

	public AdministratorDtoResponse findById(long id) {
		if (id < 0)
			throw new RuntimeException("L'id deve essere maggiore di 0");
		Administrator a = service.findById(id);
		return mapper.toDto(a);
	}

	public AdministratorDtoResponse addAdministrator(RegisterUserDTORequest request) {
		Administrator a = new Administrator();
		a.setName(request.getName());
		a.setSurname(request.getSurname());
		a.setPassword(request.getPassword());
		a.setUsername(request.getUsername());
		a.setAvailable(true);
		return mapper.toDto(service.addAdministrator(a));
	}

	public AdministratorDtoResponse updateAdministrator(AdministratorUpdateDTORequest request) {
		Administrator a = service.findById(request.getId());
		if (request.getUsername() != null)
			a.setUsername(request.getUsername());
		if (request.getPassword() != null)
			a.setPassword(request.getPassword());
		return mapper.toDto(service.updateAdministrator(a));
	}

	public AdministratorDtoResponse findByCondominiums_Id(long id) {
		if (id < 0)
			throw new RuntimeException("L'id non può essere minore di 0");
		return mapper.toDto(service.findByCondominiums_Id(id));
	}

	public List<BillDTOResponse> billSplitter( AceaBillRequest bill) {
		List<Bill> splittedBills=new ArrayList<>();
		Condominium condominium=condominiumService.findById(bill.getIdCondominium());
		double missingConsumption=bill.getCondominiumConsumption();
		List<Apartment> apartmentsWithoutScan = condominium.getApartments().stream()
				.filter(apartment -> {
					int indexOfLastScan = apartment.getScans().size();
					LocalDate dateOfLastScan = apartment.getScans().get(indexOfLastScan - 1).getScanDate();
					return bill.getDeliveryDate().getMonthValue() != dateOfLastScan.getMonthValue();
				})
				.collect(Collectors.toList());
		List<Apartment> apartmentsWithScan = condominium.getApartments().stream()
				.filter(apartment -> {
					int indexOfLastScan = apartment.getScans().size();
					LocalDate dateOfLastScan = apartment.getScans().get(indexOfLastScan - 1).getScanDate();
					return bill.getDeliveryDate().getMonthValue() == dateOfLastScan.getMonthValue();
				})
				.collect(Collectors.toList());
		for(int i=0;i<apartmentsWithScan.size();i++) {
			int indexOfLastScan = apartmentsWithScan.get(i).getScans().size();
			double apartmentConsumption = apartmentsWithScan.get(i).getScans().get(indexOfLastScan - 1).getMcLiter();
			missingConsumption-=apartmentConsumption;
			double apartmentAmount = bill.getCost() * (apartmentConsumption / bill.getCondominiumConsumption());
			Bill apartmentBill = new Bill();
			apartmentBill.setCost(apartmentAmount);
			apartmentBill.setDeliveringDay(bill.getDeliveryDate());
			apartmentBill.setPaymentDay(bill.getPaymentDate());
			apartmentBill.setScan(apartmentsWithScan.get(i).getScans().get(indexOfLastScan - 1));
			billService.addBill(apartmentBill);
			splittedBills.add(apartmentBill);
		}
		final double apartmentWithoutScanAmount = bill.getCost() * ((missingConsumption /apartmentsWithoutScan.size()) /bill.getCondominiumConsumption());
		apartmentsWithoutScan.forEach(apartment -> {
			int indexOfLastScan = apartment.getScans().size();
			Bill apartmentBill = new Bill();
			apartmentBill.setCost(apartmentWithoutScanAmount);
			apartmentBill.setDeliveringDay(bill.getDeliveryDate());
			apartmentBill.setPaymentDay(bill.getPaymentDate());
			apartmentBill.setScan(apartment.getScans().get(indexOfLastScan - 1));
			billService.addBill(apartmentBill);
			splittedBills.add(apartmentBill);
		});
		return billMapper.toBillDTOResponseList(splittedBills);
	}


	public List<CondominiumDtoResponse> getCondominiumByAdministratorId(AdministratorIdDtoRequest request) {
		return condominiumMapper.toListDto(condominiumService.getCondominiumByAdministrator_id(request.getId())
				.stream().filter(condominium -> condominium.isAvailable() == true).toList());

	}


	public String insertCondominium(CondominiumDTORequest dto) {
		condominiumService.addCondominium(condominiumMapper.toCondominium(dto));
		return "Condominio aggiunto con successo";
	}

	public String insertBill(BillDTORequest dto) {
		billService.addBill(billMapper.toBill(dto));
		return "bolletta inserita con successo";
	}

	public List<CustomerExtendedInfoDTOResponse> getCustomerByCondominiumId(long condominiumId) {
		return customerMapper.toListCustomersExtendedinfo(condominiumService.getCustomerByCondominiumId(condominiumId)
				.stream().filter(t -> t.isAvailable() == true).toList());
	}

	public void addApartment(AddApartmentDtoRequest request) {
		apartmentService.saveApartment(apartmentMapper.fromAddApartmentDtoRequestToApartment(request));
	}

	public List<ApartmentScanDTOResponse> findAllScanByCondominiumId(long condominiumId) {
		return scanMapper.toApartmentScanDtoResposneList(scanService.findAllScanByCondominiumId(condominiumId).stream()
				.filter(scan -> scan.isAvailable() == true).toList());
	}

	public void createCondominium(AddCondominiumDTORequest request) {
		Condominium condominium = condominiumMapper.fromAddCondominiumDTORequestToCondominium(request);
		for (Apartment apartment : condominium.getApartments()) {
			apartment.setCondominium(condominium);
		}
		condominiumService.addCondominium(condominium);

	}
	
	public Administrator deleteAdministrator(long id) {
		Administrator a = administratorService.findById(id);
		a.setAvailable(false);
		return administratorService.updateAdministrator(a);
	}
	
	public Condominium deleteCondominium(long id) {
		Condominium c = condominiumService.findById(id);
		c.setAvailable(false);
		List<Apartment> apartments = apartmentService.findAllApartmentByCondominiumId(id).stream().map(apartment -> {
			apartment.setAvailable(false);
			return apartment;
		}).collect(Collectors.toList());
		return condominiumService.updateCondominium(c);
	}

}
