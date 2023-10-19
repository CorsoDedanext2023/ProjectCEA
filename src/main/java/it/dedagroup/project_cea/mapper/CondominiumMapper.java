package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.CondominiumDTORequest;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.service.def.AdministratorServiceDef;

@Component
public class CondominiumMapper {

	@Autowired
	private AdministratorServiceDef administratorService;

	@Autowired
	private ApartmentMapper apartmentMapper;

	public CondominiumDtoResponse toDto(Condominium c) {
		CondominiumDtoResponse response=new CondominiumDtoResponse();
		//response.setId(c.getId());
		response.setAddress(c.getAddress());
		response.setAdmName(c.getAdministrator().getName());
		response.setAdmSurname(c.getAdministrator().getSurname());
		response.setApartments(apartmentMapper.toApartmentForCondoiminiumListDto(c.getApartments()));
		return response;
	}
	
	public List<CondominiumDtoResponse> toListDto(List<Condominium>condominiums){
		return condominiums.stream().map(this::toDto).toList();
	}

	public Condominium toCondominium(CondominiumDTORequest dto) {
		Condominium c = new Condominium();
		c.setAddress(dto.getAddress());
		c.setAdministrator(administratorService.findById(dto.getId_administrator()));
		return c;
	}
}
