package it.dedagroup.project_cea.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.dedagroup.project_cea.dto.request.AdministratorUpdateRequest;
import it.dedagroup.project_cea.dto.request.RegisterUserDto;
import it.dedagroup.project_cea.dto.response.AdministratorDtoResponse;
import it.dedagroup.project_cea.mapper.AdministratorMapper;
import it.dedagroup.project_cea.model.Administrator;
import it.dedagroup.project_cea.service.impl.AdministratorServiceImpl;
import it.dedagroup.project_cea.service.impl.CondominiumServiceImpl;

@Service
public class AdministratorFacade {
		
	@Autowired
	AdministratorServiceImpl service;
	@Autowired
	AdministratorMapper mapper;
	@Autowired
	CondominiumServiceImpl condominiumService;
	
	public AdministratorDtoResponse findById(long id) {
		if(id<0) throw new RuntimeException("L'id deve essere maggiore di 0");
		Administrator a=service.findById(id);
		return mapper.toDto(a);
	}
	
	public AdministratorDtoResponse addAdministrator(RegisterUserDto request) {
		Administrator a=new Administrator();
		a.setName(request.getName());
		a.setSurname(request.getSurname());
		a.setPassword(request.getPassword());
		a.setUsername(request.getUsername());
		a.setAvailable(true);
		return mapper.toDto(service.addAdministrator(a));
	}
	
	public AdministratorDtoResponse updateAdministrator(AdministratorUpdateRequest request) {
		Administrator a=service.findById(request.getId());
		if(request.getUsername()!=null) a.setUsername(request.getUsername());
		if(request.getPassword()!=null) a.setPassword(request.getPassword());
		return mapper.toDto(service.updateAdministrator(a));
	}
	
	public AdministratorDtoResponse findByCondominiums_Id(long id) {
		if(id<0) throw new RuntimeException("L'id non può essere minore di 0");
		return mapper.toDto(service.findByCondominiums_Id(id));
	}
	
	public void billSplitter() {
		
	}
	
}
