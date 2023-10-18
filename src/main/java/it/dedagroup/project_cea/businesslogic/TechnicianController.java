package it.dedagroup.project_cea.businesslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.facade.TechnicianFacade;

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
	public ResponseEntity<ScanDtoRequest> setScanApartment(ScanDtoRequest request){
		return ResponseEntity.status(HttpStatus.OK).body(techFac.setScanApartment(request));
	}
}
