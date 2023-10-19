package it.dedagroup.project_cea.businesslogic;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.core.status.Status;
import it.dedagroup.project_cea.dto.request.ScanDtoRequest;
import it.dedagroup.project_cea.dto.request.TechnicianRequest;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.dto.response.TechnicianDTO;
import it.dedagroup.project_cea.facade.TechnicianFacade;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.model.Technician;

@RestController
@RequestMapping("/technician")
public class TechnicianController {
	
	@Autowired
	TechnicianFacade techFac;
	
	@GetMapping("/getScans")
	public ResponseEntity<List<ScanDTOResponse>> getScans(){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.getScans());
	}
	
	@GetMapping("/setScan")
	public ResponseEntity<ScanDTOResponse> setScanApartment(ScanDtoRequest request, long idIntervention){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.addScanApartment(request, idIntervention));
	}
	
	@GetMapping("/tech/findById")
	public ResponseEntity<TechnicianDTO> findTechnicianById(@RequestBody TechnicianRequest request){
		return ResponseEntity.ok(techFac.findByUsername(request));
	}
	
	@GetMapping("/tech/findByUser")
	public ResponseEntity<TechnicianDTO> findTechnicianByUtente_Username(@RequestBody TechnicianRequest request){
		return ResponseEntity.ok(techFac.findByUsername(request));
	}
	
	@GetMapping("/tech/findByIntervention")
	public ResponseEntity<Technician> findTechnicianByIntervention(@RequestBody TechnicianRequest request){
		return ResponseEntity.ok(techFac.findByInterventionId(request));
	}
	
	@GetMapping("tech/findAll")
	public ResponseEntity<List<Technician>> findAll(){
		List<Technician> t = techFac.findAll();
		if(t!=null){
			return ResponseEntity.status(HttpStatus.OK).body(t);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/tech/update")
	public ResponseEntity<TechnicianDTO> updateTechnician(@RequestBody TechnicianRequest request){
		return ResponseEntity.ok(techFac.update(request));
	}
	
	@PostMapping("/tech/removeByUser")
	public ResponseEntity<String> removeTechnicianByUser(@RequestBody TechnicianRequest request){
		return ResponseEntity.ok(techFac.removeTechnicianByUsername(request));
	}
	
	@PostMapping("/tech/removeById")
	public ResponseEntity<String> removeTechnicianById(@RequestBody TechnicianRequest request){
		return ResponseEntity.ok(techFac.removeTechnicianById(request));
	}
}
