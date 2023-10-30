package it.dedagroup.project_cea.mapper;

import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Secretary;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.TechnicianDTORequest;
import it.dedagroup.project_cea.dto.response.TechnicianDTOResponse;
import it.dedagroup.project_cea.model.Technician;

import java.util.List;

@Component
public class TechnicianMapper {

	public TechnicianDTOResponse toTechnicianDTOResponse(Technician t) {
		if(t==null) return null;
		TechnicianDTOResponse tech = new TechnicianDTOResponse();
		tech.setId(t.getId());
		tech.setName(t.getName());
		tech.setWorkLoad(t.getMaxWorkload()); //aggiunto
		List<Long> idSecretariesList = t.getInterventions().stream()
				.map(Intervention::getSecretary).map(Secretary::getId)
				.distinct()
				.toList();
		tech.setId_secretaries(idSecretariesList);
		tech.setSurname(t.getSurname());
		tech.setUsername(t.getUsername());
		tech.setPassword(t.getPassword());
		return tech;
	}


	public List<TechnicianDTOResponse> toTechnicianDTOResponseList(List<Technician> technicians){
		return technicians.stream().map(this::toTechnicianDTOResponse).toList();
	}

	public Technician toTechnicianFromDtoRequest(TechnicianDTORequest request) {
		Technician tech = new Technician();
		tech.setId(request.getId());
		tech.setName(request.getName());
		tech.setSurname(request.getSurname());
		tech.setUsername(request.getUsername());
		tech.setPassword(request.getPassword());
		return tech;
	}

	public List<TechnicianDTOResponse> technicianDTOResponsesList(List<Technician> technicians){
		return technicians.stream().map(this::toTechnicianDTOResponse).toList();
	}
}
