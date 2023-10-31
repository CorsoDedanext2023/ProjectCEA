package it.dedagroup.project_cea.controller;

import it.dedagroup.project_cea.dto.request.*;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.dto.response.TechnicianDTOResponse;
import it.dedagroup.project_cea.facade.TechnicianFacade;
import it.dedagroup.project_cea.model.Technician;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static it.dedagroup.project_cea.util.UtilPath.*;
//TODO validate i request param e i pathvariable
@RestController
public class TechnicianController {
	
	@Autowired
	TechnicianFacade techFac;

	@GetMapping(GET_SCAN_PATH)
	public ResponseEntity<List<ScanDTOResponse>> getScans(){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.getAllScans());
	}
	
	@PostMapping(SET_SCAN_PATH)
	public ResponseEntity<String> setScan(@Valid @RequestBody ScanDtoRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.addScan(request));
	}
	
	@GetMapping(FIND_BY_ID_PATH)
	public ResponseEntity<TechnicianDTOResponse> findTechnicianById(@RequestParam long idTech){
		return ResponseEntity.ok(techFac.findById(idTech));
	}
	
	@GetMapping(FIND_BY_USER_PATH)
	public ResponseEntity<TechnicianDTOResponse> findTechnicianByUsername(@RequestParam String username){
		return ResponseEntity.ok(techFac.findByUsername(username));
	}
	
	@GetMapping(FIND_BY_INTERVENTIONS_PATH)
	public ResponseEntity<TechnicianDTOResponse> findByInterventionId(@RequestParam long idIntervention){
		return ResponseEntity.ok(techFac.findByInterventionId(idIntervention));
	}
	
	@GetMapping(FIND_ALL_PATH)
	public ResponseEntity<List<TechnicianDTOResponse>> findAll(){
		List<TechnicianDTOResponse> t = techFac.findAll();
		if(t!=null){
			return ResponseEntity.status(HttpStatus.OK).body(t);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping(FIND_FREE_PATH)
	public ResponseEntity<List<TechnicianDTOResponse>> findFree(){
		List<TechnicianDTOResponse> list = techFac.findFree();
		if(list!=null){
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping(UPDATE_PATH)
	public ResponseEntity<TechnicianDTOResponse> updateTechnician(@Valid @RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.update(request));
	}
	/**
	 * Questo metodo simula la cancellazione di un tecnico, tramite il suo ID, dal nostro database evitanto la cancellazione effettiva
	 * ma semplicemente settando la variabile "isAvailable" dell'oggetto {@link Technician} a false.
	 * @param username Richiede in input un username del tecnico.
	 * @return Ritorna una stringa in caso di "cancellazione" effetuata con successo.
	 */
	@PostMapping(REMOVE_BY_USER_PATH)
	public ResponseEntity<String> removeTechnicianByUsername(@RequestParam String username){
		return ResponseEntity.ok(techFac.removeByUsername(username));
	}
	
	@PostMapping(REMOVE_BY_ID_PATH)
	public ResponseEntity<String> removeTechnicianById(@RequestParam long id){
		return ResponseEntity.ok(techFac.removeById(id));
	}

	@PostMapping(REBOOK_INTERVENTION_PATH)
	public ResponseEntity<InterventionDTOResponse> rebookIntervention(@Valid @RequestBody InterventionRebookDTORequest request){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.rebookIntervention(request));
	}
}
