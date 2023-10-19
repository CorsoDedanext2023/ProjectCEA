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
	public ResponseEntity<TechnicianDTOResponse> findTechnicianById(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.findByUsername(request));
	}
	
	@GetMapping("/tech/findByUser")
	public ResponseEntity<TechnicianDTOResponse> findTechnicianByUtente_Username(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.findByUsername(request));
	}
	
	@GetMapping("/tech/findByIntervention")
	public ResponseEntity<Technician> findTechnicianByIntervention(@RequestBody TechnicianDTORequest request){
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
	public ResponseEntity<TechnicianDTOResponse> updateTechnician(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.update(request));
	}
	
	@PostMapping("/tech/removeByUser")
	public ResponseEntity<String> removeTechnicianByUser(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.removeTechnicianByUsername(request));
	}
	
	@PostMapping("/tech/removeById")
	public ResponseEntity<String> removeTechnicianById(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.removeTechnicianById(request));
	}
}
