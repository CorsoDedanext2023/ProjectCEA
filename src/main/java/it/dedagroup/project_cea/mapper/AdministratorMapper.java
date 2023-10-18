package it.dedagroup.project_cea.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.AdministratorDtoResponse;
import it.dedagroup.project_cea.exception.model.NotValidDataException;
import it.dedagroup.project_cea.model.Administrator;

@Component
public class AdministratorMapper {
	
	@Autowired
	CondominiumMapper condominiumMapper;
	
	
	public AdministratorDtoResponse toDto(Administrator admin) {
		if (admin==null)throw new NotValidDataException("Admin is empty");
		AdministratorDtoResponse a = new AdministratorDtoResponse();
		a.setName(admin.getName());
		a.setCondominiums(condominiumMapper.toListDto(admin.getCondominiums()));
		return a;
	}
}
