package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.ApartmentDto;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Apartment;

@Component
public class ApartmentMapper {
	public ApartmentDto toDto(Apartment a) {
		if (a == null)throw new NotValidDataException("Apartment is empty: "+a);
		ApartmentDto apart = new ApartmentDto();
		//Da implementare
		return apart;
	}
	public List<ApartmentDto> toListDto(List<Apartment> apart){
		if (apart == null || apart.isEmpty())throw new NotValidDataException("List of apartments is empty: "+apart);
		return apart.stream().map(this::toDto).toList();
	}
}
