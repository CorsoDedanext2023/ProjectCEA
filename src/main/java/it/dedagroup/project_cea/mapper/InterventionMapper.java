package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.InterventionDTORequest;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.model.Intervention;

@Component
public class InterventionMapper {
	
	public InterventionDTOResponse toInterventionDTOResponse(Intervention i) {
		InterventionDTOResponse intDTOResp = new InterventionDTOResponse();
		intDTOResp.setDate(i.getInterventionDate());
		intDTOResp.setType(i.getType());
		intDTOResp.setStatus(i.getStatus());
		intDTOResp.setCondominiumAddress(i.getApartment().getCondominium().getAddress());
		intDTOResp.setUnitNumber(i.getApartment().getUnitNumber());
		intDTOResp.setFloorNumber(i.getApartment().getFloorNumber());
		intDTOResp.setCustomerName(i.getApartment().getCustomer().getName());
		intDTOResp.setCustomerSurname(i.getApartment().getCustomer().getSurname());
		intDTOResp.setTechnicianName(i.getTechnician().getName());
		intDTOResp.setTechnicianSurname(i.getTechnician().getSurname());
		return intDTOResp;
	}
	
	public List<InterventionDTOResponse> toInterventionDTOResponseList(List<Intervention> interventions){
		return interventions.stream().map(this::toInterventionDTOResponse).toList();
	}
	
	public Intervention toIntervention(InterventionDTORequest i) {
		Intervention intervention = new Intervention();
		intervention.setInterventionDate(i.getInterventionDate());
		intervention.setType(i.getType());
		intervention.setStatus(i.getStatus());
		intervention.setApartment(i.getApartment());
		intervention.setTechnician(i.getTechnician());
		return intervention;
	}
	

}
