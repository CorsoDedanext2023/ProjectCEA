package it.dedagroup.project_cea.mapper;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.TechnicianDTO;
import it.dedagroup.project_cea.model.Technician;

@Component
public class TechnicianMapper {
	
	public TechnicianDTO toDTO(Technician t) {
		if(t==null) return null;
		TechnicianDTO tecnico = new TechnicianDTO();
		tecnico.setId(t.getId());
		tecnico.setNome(t.getName());
		tecnico.setCognome(t.getSurname());
		tecnico.setUsername(t.getUsername());
		tecnico.setPassword(t.getPassword());
		return tecnico;
	}
}
