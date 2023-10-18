package it.dedagroup.project_cea.mapper;

import java.util.List;

import it.dedagroup.project_cea.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.model.Condominium;

@Component
public class CondominiumMapper {

	@Autowired
	private ApartmentMapper apartmentMapper;

	public CondominiumDtoResponse toDto(Condominium c) {
		CondominiumDtoResponse response=new CondominiumDtoResponse();
		response.setId(c.getId());
		response.setAddress(c.getAddress());
		response.setAdministratorName(c.getAdministrator().getName());
		response.setApartments(apartmentMapper.toApartmentForCondoiminiumListDto(c.getApartments()));
		return response;
	}
	
	public List<CondominiumDtoResponse> toListDto(List<Condominium>condominiums){
		return condominiums.stream().map(this::toDto).toList();
	}
}
