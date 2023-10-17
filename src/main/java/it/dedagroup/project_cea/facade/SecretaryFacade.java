package it.dedagroup.project_cea.facade;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.mapper.BillMapper;
import it.dedagroup.project_cea.mapper.InterventionMapper;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;
import it.dedagroup.project_cea.service.def.BillServiceDef;
import it.dedagroup.project_cea.service.def.CondominiumServiceDef;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;
import it.dedagroup.project_cea.service.def.SecretaryServiceDef;
import it.dedagroup.project_cea.service.def.TechnicianServiceDef;

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
	
	@Autowired
	ApartmentServiceDef apartmentService;

	@Autowired
	SecretaryServiceDef secretaryService;

	@Autowired
	TechnicianServiceDef technicianService;

	@Autowired
	InterventionServiceDef interventionService;
	
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

	public void RemoteScan(long idApartment, int liters, LocalDate scanDate){
		Apartment apartment = apartmentService.findById(idApartment);
		if (apartment == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Apartment not found");
		}
		Scan scan = new Scan();
        scan.setApartment(apartment);
		scan.setScanDate(scanDate);
        scan.setMcLiter(liters);
	}

	public void addIntervention(LocalDate date, long idApartment, String description, TypeOfIntervention type, long idSecretary, long idTechnician) {

    Apartment apartment = apartmentService.findById(idApartment);

    if (apartment == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Apartment not found");
    }


    Intervention maintenance = new Intervention();

    maintenance.setDate(date);
    maintenance.setDesctiption(description); 
    maintenance.setStatus(StatusIntervention.ACCEPTED);
    maintenance.setType(type);

    Secretary secretary = secretaryService.findById(idSecretary);
    Technician technician = technicianService.findById(idTechnician);

    if (secretary == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Secretary not found");
    }

    if (technician == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Technician not found");
    }


    maintenance.setSecretary(secretary);
    maintenance.setTechnician(technician);

    //interventionService.save(maintenance);
}

}
