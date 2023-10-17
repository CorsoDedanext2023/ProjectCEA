package it.dedagroup.project_cea.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.mapper.BillMapper;
import it.dedagroup.project_cea.mapper.InterventionMapper;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.service.def.BillServiceDef;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;

@Service
public class SecretaryFacade {
	
	@Autowired
	BillServiceDef billServ;
	
	@Autowired
	CondominiumServiceDef condServ;
	
	@Autowired
	BillMapper billMap;
	
	@Autowired
	InterventionServiceDef intervServ;
	
	@Autowired
	InterventionMapper intMap;
	
	//metodo per vedere tutte le bollette di un determinato condominio tramite il suo id
	public List<BillDTOResponse> getAllBillsOfCondominium(long idCondominium){
		//stream per filtrare le bollette e ottenere solo quelle che appartengono al condominio desiderato
		List<Bill> billsOfCondominium = billServ.findAll().stream().filter(b -> b.getMeter().getApartment().getCondominium().getId() == idCondominium).toList();
		if(billsOfCondominium.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "no bills found for this condominium");
		}
		else {
			return billMap.toBillDTOResponseList(billsOfCondominium);
		}
	}
	
	public List<InterventionDTOResponse> getInterventionListPerType(TypeOfIntervention type){
		List<Intervention> interventionsOfType = intervServ.findAllByType(type);
		
		if(interventionsOfType.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "no interventions found of this type");
		}
		else {
			return intMap.toInterventionDTOResponseList(interventionsOfType);
		}
	}
}
