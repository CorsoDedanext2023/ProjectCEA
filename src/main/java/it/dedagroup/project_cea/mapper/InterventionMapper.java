package it.dedagroup.project_cea.mapper;

import java.time.LocalDate;
import java.util.List;

import it.dedagroup.project_cea.dto.request.BookInterventionDto;
import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;
import it.dedagroup.project_cea.service.def.TechnicianServiceDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.InterventionDTORequest;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.model.Intervention;

@Component
public class InterventionMapper {
	@Autowired
	ApartmentServiceDef apartmentServiceDef;
	@Autowired
	TechnicianServiceDef techServ;
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

	public Intervention fromBookInterventionDTORequestToIntervention(BookInterventionDto request){
		LocalDate interventionDate = LocalDate.parse(request.getInterventionDate());
		Intervention intervention=new Intervention();
		intervention.setApartment(apartmentServiceDef.findApartmentByCondominiumIdAndCustomerId(request.getIdCondominium(), request.getIdCustomer()));
		intervention.setInterventionDate(interventionDate);
		intervention.setStatus(StatusIntervention.PENDING);
		intervention.setType(TypeOfIntervention.FIXING_UP);
		return intervention;
	}

	public Intervention toIntervention(InterventionDTORequest i) {
		Intervention intervention = new Intervention();
		intervention.setInterventionDate(i.getInterventionDate());
		intervention.setType(i.getType());
		intervention.setStatus(i.getStatus());
		intervention.setApartment(apartmentServiceDef.findById(i.getIdApartment()));
		intervention.setTechnician(techServ.findById(i.getIdTechnician()));
		return intervention;
	}
	

}
