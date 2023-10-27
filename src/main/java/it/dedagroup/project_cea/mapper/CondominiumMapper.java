package it.dedagroup.project_cea.mapper;

import java.util.List;

import it.dedagroup.project_cea.dto.request.AddCondominiumDTORequest;
import it.dedagroup.project_cea.dto.response.CondominiumForAdministratorDtoResponse;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.service.impl.CondominiumServiceImpl;
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
	@Autowired
	private CondominiumServiceImpl condominiumService;

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

	public CondominiumForAdministratorDtoResponse toCondominiumForAdministratorDto(Condominium c) {
		CondominiumForAdministratorDtoResponse response=new CondominiumForAdministratorDtoResponse();
		response.setId(c.getId());
		response.setAddress(c.getAddress());
		response.setAdministratorName(c.getAdministrator().getName());
		response.setApartments(apartmentMapper.toApartmentForCondoiminiumListDto(c.getApartments()));
		return response;
	}
	//TODO creare il tolistdto del metodo qui sopra

	public Condominium fromAddCondominiumDTORequestToCondominium(AddCondominiumDTORequest request){
		Condominium condominium=new Condominium();
		condominium.setAddress(request.getAddress());
		condominium.setAdministrator(administratorService.findAdministratorById(request.getAdministrator_id()));
		condominium.setAvailable(true);
		condominium.setApartments(apartmentMapper.fromListDtoToApartmentList(request.getApartmentList()));
		return condominium;
	}


}
