package it.dedagroup.project_cea.controller;

import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.facade.SecretaryFacade;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static it.dedagroup.project_cea.util.UtilPath.*;

@RestController
public class SecretaryController {
	
	@Autowired
	SecretaryFacade secFac;
	
	@GetMapping(GET_ALL_BILLS_OF_CONDOMINIUM_PATH+"{idCondominium}")
	public ResponseEntity<List<BillDTOResponse>> getAllBillsOfCondominium(@PathVariable long idCondominium){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getAllBillsOfCondominium(idCondominium));
	}
	
	@GetMapping(GET_INTERVENTION_LIST_PER_TYPE_PATH+"{interv}")
	public ResponseEntity<List<InterventionDTOResponse>> getInterventionListPerType(@PathVariable TypeOfIntervention interv){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getInterventionListPerType(interv));
	}
	
	@GetMapping(GET_SCANS_PATH)
	public ResponseEntity<List<ScanDTOResponse>> getScans(){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getScans());
	}

	@GetMapping(REMOTE_SCAN_PATH+"{idApartment}/{liters}/{scanDate}")
	public ResponseEntity<Scan> remoteScan(@PathVariable Long idApartment, Double liters, LocalDate scanDate) {
		if (liters == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(secFac.RemoteScan(idApartment, liters, scanDate));
	}

	@PostMapping(WORKLOAD_PATH + "{maxWorkload}")
	public ResponseEntity<Integer> setWorkload(@PathVariable int maxWorkload) {
		if (maxWorkload >= 0) {
			secFac.setWorkload(maxWorkload);
			return ResponseEntity.status(HttpStatus.OK).body(maxWorkload);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PostMapping(ACCEPT_PENDING_INTERVENTION_PATH+"{idApartment}/{idIntervention}")
	public ResponseEntity<InterventionDTOResponse> acceptPendingIntervention(@PathVariable long idApartment, @PathVariable long idIntervention){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(secFac.acceptPendingIntervention(idApartment, idIntervention));
	}

	@GetMapping(LIST_OF_CONDOMINIUM_OF_TECHNICIAN_INTERVENTIONS_PATH+"{idTechnician}")
		public ResponseEntity<List<CondominiumDtoResponse>> listOfCondominiumOfInterventionsOfTechnician(@PathVariable long idTechnician){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(secFac.listaCondominiDiInterventiTecnico(idTechnician));
		}

	//ritorna la lista di interventi in carico a un determinato tecnico,
	// ordinata per data e priorità di tipo di intervento(prima mostra le letture e poi la manutenzione)
	@GetMapping("/interventionsOfTechnicianByDateAndPriority/{idTechnician}")
	public ResponseEntity<List<InterventionDTOResponse>> interventionsOfTechnicianByDateAndPriority(@PathVariable long idTechnician){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.interventionsOfTechnicianByDateAndPriority(idTechnician));
	}
}
