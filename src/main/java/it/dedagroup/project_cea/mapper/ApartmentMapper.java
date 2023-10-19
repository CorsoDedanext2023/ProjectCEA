package it.dedagroup.project_cea.mapper;

import java.util.ArrayList;
import java.util.List;

import it.dedagroup.project_cea.dto.request.AddApartmentDtoRequest;
import it.dedagroup.project_cea.dto.response.ApartmentForCondominiumDtoResponse;
import it.dedagroup.project_cea.service.impl.CondominiumServiceImpl;
import it.dedagroup.project_cea.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.ApartmentDTOResponse;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;

@Component
public class ApartmentMapper {

	@Autowired
	CustomerServiceImpl customerService;
	@Autowired
	CondominiumServiceImpl condominiumService;

	public ApartmentDTOResponse toDto(Apartment a) {
		if (a == null)throw new NotValidDataException("Apartment is empty: "+a);
		ApartmentDTOResponse apart = new ApartmentDTOResponse();
		apart.setId(a.getId());
		apart.setUnitNumber(a.getUnitNumber());
		apart.setFloorNumber(a.getFloorNumber());
		apart.setId_customer(a.getCustomer().getId());
		apart.setId_condominium(a.getCondominium().getId());
		return apart;
	}
	public List<ApartmentDTOResponse> toListDto(List<Apartment> apart){
		if (apart == null || apart.isEmpty())throw new NotValidDataException("List of apartments is empty: "+apart);
		return apart.stream().map(this::toDto).toList();
	}

	public ApartmentForCondominiumDtoResponse fromApartmentToApartmenForCondominiumtDto(Apartment a){
		ApartmentForCondominiumDtoResponse response=new ApartmentForCondominiumDtoResponse();
		response.setId(a.getId());
		response.setUnitNumber(a.getUnitNumber());
		response.setFloorNumber(a.getFloorNumber());
		response.setCustomerName(a.getCustomer().getName());
		response.setCustomerSurname(a.getCustomer().getSurname());
		return response;
	}

	public List<ApartmentForCondominiumDtoResponse> toApartmentForCondoiminiumListDto(List<Apartment> apartments){
		return apartments.stream().map(this::fromApartmentToApartmenForCondominiumtDto).toList();
	}

	public Apartment fromAddApartmentDtoRequestToApartment(AddApartmentDtoRequest request){
		Apartment apartment=new Apartment();
		apartment.setAvailable(true);
		apartment.setCustomer(customerService.findCustomerById(request.getId_customer()));
		apartment.setCondominium(condominiumService.findById(request.getId_condominium()));
		apartment.setFloorNumber(request.getFloorNumber());
		apartment.setUnitNumber(request.getUnitNumber());
		apartment.setScans(new ArrayList<>());
		apartment.setInterventions(new ArrayList<>());
		return apartment;
	}
}
