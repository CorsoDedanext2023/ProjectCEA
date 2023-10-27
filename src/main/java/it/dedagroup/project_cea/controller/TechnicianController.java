package it.dedagroup.project_cea.controller;

import it.dedagroup.project_cea.dto.request.ScanDtoRequest;
import it.dedagroup.project_cea.dto.request.TechnicianDTORequest;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.dto.response.TechnicianDTOResponse;
import it.dedagroup.project_cea.facade.TechnicianFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	
//	@PostMapping(SET_SCAN_PATH)
//	public ResponseEntity<String> setScan(@RequestBody long id, ScanDtoRequest request){
//		return ResponseEntity.status(HttpStatus.OK).body(techFac.addScan(request));
//	}
	
	@GetMapping(FIND_BY_ID_PATH)
	public ResponseEntity<TechnicianDTOResponse> findTechnicianById(@RequestParam long idTech){
		return ResponseEntity.ok(techFac.findById(idTech));
	}
	
	@GetMapping(FIND_BY_USER_PATH)
	public ResponseEntity<TechnicianDTOResponse> findTechnicianByUtente_Username(@RequestParam String username){
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
	public ResponseEntity<TechnicianDTOResponse> updateTechnician(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.update(request));
	}
	
	@PostMapping(REMOVE_BY_USER_PATH)
	public ResponseEntity<String> removeTechnicianByUser(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.removeByUsername(request));
	}
	
	@PostMapping(REMOVE_BY_ID_PATH)
	public ResponseEntity<String> removeTechnicianById(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.removeById(request));
	}
}
