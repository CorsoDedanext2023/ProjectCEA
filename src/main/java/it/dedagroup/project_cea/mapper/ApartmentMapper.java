package it.dedagroup.project_cea.mapper;

import java.util.List;

import it.dedagroup.project_cea.dto.response.ApartmentForCondominiumDtoResponse;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.ApartmentDTOResponse;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;

@Component
public class ApartmentMapper {
	public ApartmentDTOResponse toDto(Apartment a) {
		if (a == null)throw new NotValidDataException("Apartment is empty: "+a);
		ApartmentDTOResponse apart = new ApartmentDTOResponse();
		//TODO Da implementare
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
}
