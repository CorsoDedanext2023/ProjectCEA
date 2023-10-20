package it.dedagroup.project_cea.businesslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.request.ScanDtoRequest;
import it.dedagroup.project_cea.dto.request.TechnicianDTORequest;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.dto.response.TechnicianDTOResponse;
import it.dedagroup.project_cea.facade.TechnicianFacade;
import it.dedagroup.project_cea.model.Technician;
import static it.dedagroup.project_cea.util.UtilPath.*;

@RestController
@RequestMapping("/technician")
public class TechnicianController {
	
	@Autowired
	TechnicianFacade techFac;
	
	@GetMapping(GET_SCAN_PATH)
	public ResponseEntity<List<ScanDTOResponse>> getScans(){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.getScans());
	}
	
	@GetMapping(SET_SCAN_PATH)
	public ResponseEntity<ScanDTOResponse> setScanApartment(ScanDtoRequest request, long idIntervention){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.addScanApartment(request, idIntervention));
	}
	
	@GetMapping(FIND_BY_ID_PATH)
	public ResponseEntity<TechnicianDTOResponse> findTechnicianById(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.findByUsername(request));
	}
	
	@GetMapping(FIND_BY_USER_PATH)
	public ResponseEntity<TechnicianDTOResponse> findTechnicianByUtente_Username(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.findByUsername(request));
	}
	
	@GetMapping(FIND_BY_INTERVENTIONS_PATH)
	public ResponseEntity<Technician> findTechnicianByIntervention(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.findByInterventionId(request));
	}
	
	@GetMapping(FIND_ALL_PATH)
	public ResponseEntity<List<Technician>> findAll(){
		List<Technician> t = techFac.findAll();
		if(t!=null){
			return ResponseEntity.status(HttpStatus.OK).body(t);
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
		return ResponseEntity.ok(techFac.removeTechnicianByUsername(request));
	}
	
	@PostMapping(REMOVE_BY_ID_PATH)
	public ResponseEntity<String> removeTechnicianById(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.removeTechnicianById(request));
	}
}
