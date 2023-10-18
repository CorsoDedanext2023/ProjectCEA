package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.CondominiumDto;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.service.def.AdministratorServiceDef;

@Component
public class CondominiumMapper {
	
	@Autowired
	private AdministratorServiceDef administratorService;

	public CondominiumDtoResponse toDto(Condominium c) {
		return null;
	}
	
	public List<CondominiumDtoResponse> toListDto(List<Condominium>condominiums){
		return null;
	}
	
	public Condominium toCondominium(CondominiumDto dto) {
		Condominium c = new Condominium();
		c.setAddress(dto.getAddress());
		c.setAdministrator(administratorService.findById(dto.getId_administrator()));
		return c;
	}
}
