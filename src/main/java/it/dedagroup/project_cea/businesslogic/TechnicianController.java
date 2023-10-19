package it.dedagroup.project_cea.businesslogic;

import java.util.List;

import it.dedagroup.project_cea.dto.request.InterventionDTORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<List<ScanDTOResponse>> getAllScans(){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.getAllScans());
	}

	@GetMapping("/setScan")
	public ResponseEntity<String> addScanApartment(ScanDtoRequest request, long idIntervention){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.addScan(request));
	}

	@GetMapping("/tech/findById")
	public ResponseEntity<TechnicianDTOResponse> findTechnicianById(@RequestParam int idTech){
		return ResponseEntity.ok(techFac.findById(idTech));
	}

	@GetMapping("/tech/findByUser")
	public ResponseEntity<TechnicianDTOResponse> findByUsername(@RequestParam String username){
		return ResponseEntity.ok(techFac.findByUsername(username));
	}

	@GetMapping("/tech/findByIntervention")
	public ResponseEntity<TechnicianDTOResponse> findByInterventionId(@RequestParam int idIntervention){
		return ResponseEntity.ok(techFac.findByInterventionId(idIntervention));
	}

	@GetMapping("tech/findAll")
	public ResponseEntity<List<TechnicianDTOResponse>> findAll(){
		List<TechnicianDTOResponse> t = techFac.findAll();
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
		return ResponseEntity.ok(techFac.removeByUsername(request));
	}

	@PostMapping("/tech/removeById")
	public ResponseEntity<String> removeTechnicianById(@RequestBody TechnicianDTORequest request){
		return ResponseEntity.ok(techFac.removeById(request));
	}

}
