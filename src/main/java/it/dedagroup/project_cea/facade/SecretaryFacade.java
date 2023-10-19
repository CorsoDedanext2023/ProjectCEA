package it.dedagroup.project_cea.facade;

import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.mapper.BillMapper;
import it.dedagroup.project_cea.mapper.InterventionMapper;
import it.dedagroup.project_cea.mapper.ScanMapper;
import it.dedagroup.project_cea.model.*;
import it.dedagroup.project_cea.service.def.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

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
	ScanServiceDef scanServ;
	
	@Autowired
	ScanMapper scanMap;

	@Autowired
	TechnicianServiceDef techService;

	@Autowired
	ApartmentServiceDef apartmentService;

	//metodo per vedere tutte le bollette di un determinato condominio tramite il suo id
	public List<BillDTOResponse> getAllBillsOfCondominium(long idCondominium){
		//stream per filtrare le bollette e ottenere solo quelle che appartengono al condominio desiderato
		List<Bill> billsOfCondominium = billServ.findAll().stream().filter(b -> b.getScan().getApartment().getCondominium().getId() == idCondominium).toList();
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
	
	public List<ScanDTOResponse> getScans(){
		List<Scan> allScans = scanServ.findAll().stream().filter(sc -> sc.isAvailable()).toList();
		if(allScans.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "no scans found in database");
		}
		else {
			return scanMap.toScanDTOResponseList(allScans);
		}
	}

	public InterventionDTOResponse acceptPendingIntervention(long idApartment, long idIntervention){
		Intervention interv = intervServ.findById(idIntervention); //caso intervention null gestito in serviceImpl
		if(interv.getApartment().getId() != idApartment){
			throw new ResponseStatusException(HttpStatus.CONFLICT, "the id of the apartment doesn't match");
		}
		if(!interv.getStatus().equals(StatusIntervention.PENDING)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "can't accept an intervention which is not pending");
		}
		interv.setStatus(StatusIntervention.ACCEPTED);
		intervServ.save(interv);
		return intMap.toInterventionDTOResponse(interv);
	}



	// Creates a new scan for a specified apartment with the given liters and scan date
	public Scan RemoteScan(Long idApartment, double liters, LocalDate scanDate) {
		Apartment apartment = apartmentService.findById(idApartment);
		if (apartment == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Apartment not found");
		}
		Scan scan = new Scan();
		scan.setApartment(apartment);
		scan.setScanDate(scanDate);
		scan.setMcLiter(liters);
		return scan;
	}

	// This method assigns a specified number of interventions to technicians
	public void setWorkload(int workload) {
		List<Intervention> interventions = intervServ.findall(): // interventions team must create the findAll method.
		List<Technician> technicians = techService.findAll();

		int interventionsPerTechnician = workload / technicians.size();

		int remainingInterventions = workload;

		for (Technician technician : technicians) {
			if (remainingInterventions <= 0) {
				break;
			}

			int interventionsToAssign = Math.min(interventionsPerTechnician, remainingInterventions);

			for (int i = 0; i < interventionsToAssign; i++) {
				if (interventions.isEmpty()) {
					break;
				}

				Intervention intervention = interventions.remove(0);
				assignIntervention(intervention, technician);
				remainingInterventions--;
			}
		}
	}

	// Assigns an intervention to a technician and updates the technician's intervention list
	public void assignIntervention(Intervention intervention, Technician technician) {
		List<Intervention> interventions = technician.getInterventions();
		interventions.add(intervention);
		technician.setInterventions(interventions);
	}
}
