package it.dedagroup.project_cea.businesslogic;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.request.ScanDtoRequest;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.facade.TechnicianFacade;
import it.dedagroup.project_cea.model.Condominium;
import it.dedagroup.project_cea.model.Scan;

@RestController
@RequestMapping("/technician")
public class TechnicianController {
	
	@Autowired
	TechnicianFacade techFac;
	
	@GetMapping("/getScans")
	public ResponseEntity<List<ScanDTOResponse>> getScans(){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.getScans());
	}
	
	@GetMapping("setScan")
	public ResponseEntity<ScanDTOResponse> setScanApartment(ScanDtoRequest request, long idIntervention){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.addScanApartment(request, idIntervention));
	}
	
	@GetMapping("getCondominiumsList")
	public ResponseEntity<Condominium> getCondominiumsListByInterventions(long idTechnician, LocalDate interventionDate){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.getCondominiumListByInterventions(long idTechnician, LocalDate interventionDate));
	}
}
