package it.dedagroup.project_cea.controller;

import it.dedagroup.project_cea.dto.request.InterventionUpdateDTORequest;
import it.dedagroup.project_cea.dto.response.*;
import it.dedagroup.project_cea.facade.SecretaryFacade;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static it.dedagroup.project_cea.util.UtilPath.*;
//TODO validate i request param e i pathvariable
@Validated
@RestController
public class SecretaryController {
	
	@Autowired
	SecretaryFacade secFac;
	
	@GetMapping(GET_ALL_BILLS_OF_CONDOMINIUM_PATH+"{idCondominium}")
	public ResponseEntity<List<BillDTOResponse>> getAllBillsOfCondominium(@PathVariable @Min(value = 1, message = "min id input is 1") long idCondominium){
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
	public ResponseEntity<Scan> remoteScan(@PathVariable Long idApartment, @PathVariable Double liters, @PathVariable LocalDate scanDate) {
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

	@PostMapping(ACCEPT_PENDING_INTERVENTION_PATH+"{idIntervention}")
	public ResponseEntity<InterventionDTOResponse> acceptPendingIntervention(@PathVariable long idIntervention){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(secFac.acceptPendingIntervention(idIntervention));
	}

	@GetMapping(LIST_OF_CONDOMINIUM_OF_TECHNICIAN_INTERVENTIONS_PATH+"{idTechnician}")
		public ResponseEntity<List<CondominiumDtoResponse>> condominiumsWhereTechnicianDidInterventions(@PathVariable long idTechnician){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(secFac.condominiumsWhereTechnicianDidInterventions(idTechnician));
		}

	//ritorna la lista di interventi in carico a un determinato tecnico,
	// ordinata per data e priorità di tipo di intervento(prima mostra le letture e poi la manutenzione)
	@GetMapping(INTERVENTIONS_OF_TECHNICIAN_BY_DATE_AND_PRIORITY+"{idTechnician}")
	public ResponseEntity<List<InterventionDTOResponse>> interventionsOfTechnicianByDateAndPriority(@PathVariable long idTechnician){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.interventionsOfTechnicianByDateAndPriority(idTechnician));
	}

	//prende lista di bollette di un cliente
	@GetMapping(GET_ALL_BILLS_OF_CUSTOMER + "{idCustomer}")
	public ResponseEntity<List<BillDTOResponse>> getAllBillsOfCustomer(@PathVariable long idCustomer){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getAllBillsOfCustomer(idCustomer));
	}


	//vedere facade per dettagli
	//ritorna la lista di bollette del cliente che non sono state ancora pagate
	@GetMapping(GET_ALL_UNPAID_BILLS_OF_CUSTOMER + "{idCustomer}")
	public ResponseEntity<List<BillDTOResponse>> getAllUnpaidBillsOfCustomer(@PathVariable long idCustomer){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getAllUnpaidBillsOfCustomer(idCustomer));
	}


	//ritorna una lista di interventi da effettuare per un determinato cliente in futuro
	// (vedere facade per ulteriori dettagli)
	@GetMapping(GET_ALL_FUTURE_INTERVENTIONS_OF_CUSTOMER + "{idCustomer}")
	public ResponseEntity<List<InterventionDTOResponse>> getAllFutureInterventionsOfCustomer(@PathVariable long idCustomer){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getAllFutureInterventionsOfCustomer(idCustomer));
	}

	//aggiunte settimana 23-10
	@GetMapping( GET_SECRETARIES_ASSOCIATED_TO_TECHNICIAN + "{idTechnician}")
	public ResponseEntity<List<SecretaryDTOResponse>> getSecretariesOfTechnician(@PathVariable long idTechnician){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getSecretariesOfTechnician(idTechnician));
	}

	@PostMapping(CHANGE_TECHNICIAN_ASSIGNED_TO_INTERVENTION + "{idIntervention}")
	public ResponseEntity<InterventionDTOResponse> changeTechnicianAssignedToIntervention(@RequestParam String name, @RequestParam String surname, @PathVariable long idIntervention){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.changeTechnicianAssignedToIntervention(name, surname, idIntervention));
	}

	@GetMapping(GET_ALL_INTERVENTIONS_SORTED_BY_DATE)
	public ResponseEntity<List<InterventionDTOResponse>> getAllInterventionsSortedByDate(){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getAllInterventionsSortedByDate());
	}

	@PostMapping(UPDATE_INTERVENTION)
	public ResponseEntity<InterventionDTOResponse> updateIntervention(@Valid @RequestBody InterventionUpdateDTORequest request){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.editIntervention(request));
	}

	@GetMapping(GET_ALL_TECHNICIANS_AVAILABLE_IN_A_DATE + "{date}")
	public ResponseEntity<List<TechnicianDTOResponse>> getAllTechniciansAvailableInADate(@PathVariable LocalDate date){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getAllAvailableTechniciansInADate(date));
	}

	@GetMapping(GET_ALL_INTERVENTIONS_OF_DATE_OF_TECHNICIAN + "{idTechnician}/{date}")
	public ResponseEntity<List<InterventionDTOResponse>> getInterventionsOfDateOfTechnician(@PathVariable long idTechnician, @PathVariable LocalDate date){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getInterventionsOfDateOfTechnician(idTechnician, date));
	}
}
