package it.dedagroup.project_cea.mapper;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.TechnicianDTORequest;
import it.dedagroup.project_cea.dto.response.TechnicianDTOResponse;
import it.dedagroup.project_cea.model.Technician;

@Component
public class TechnicianMapper {
	
	public TechnicianDTOResponse toDTO(Technician t) {
		if(t==null) return null;
		TechnicianDTOResponse tech = new TechnicianDTOResponse();
		tech.setId(t.getId());
		tech.setNome(t.getName());
		tech.setCognome(t.getSurname());
		tech.setUsername(t.getUsername());
		tech.setPassword(t.getPassword());
		return tech;
	}

	public Technician toTechnicianFromDto(TechnicianDTORequest request) {
		Technician tech = new Technician();
		tech.setId(request.getId());
		tech.setName(request.getName());
		tech.setSurname(request.getSurname());
		tech.setUsername(request.getUsername());
		tech.setPassword(request.getPassword());
		return tech;
	}
}
