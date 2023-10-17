package it.dedagroup.project_cea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.facade.SecretaryFacade;
import it.dedagroup.project_cea.model.TypeOfIntervention;

@RestController
@RequestMapping("/secretary")
public class SecretaryController {
	
	@Autowired
	SecretaryFacade secFac;
	
	@GetMapping("/getAllBillsOfCondominium/{idCondominium}")
	public ResponseEntity<List<BillDTOResponse>> getAllBillsOfCondominium(@PathVariable long idCondominium){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getAllBillsOfCondominium(idCondominium));
	}
	
	@GetMapping("/getInterventionListPerType/{interv}")
	public ResponseEntity<List<InterventionDTOResponse>> getInterventionListPerType(@PathVariable TypeOfIntervention interv){
		return ResponseEntity.status(HttpStatus.OK).body(secFac.getInterventionListPerType(interv));
	}

}
