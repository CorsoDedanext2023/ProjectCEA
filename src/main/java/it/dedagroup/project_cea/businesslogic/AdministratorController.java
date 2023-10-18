package it.dedagroup.project_cea.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.request.BillRequestDto;
import it.dedagroup.project_cea.dto.request.CondominiumDto;
import it.dedagroup.project_cea.facade.AdministratorFacade;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private AdministratorFacade administratorFacade;
	
	//ENDPOINT DI INSERIMENTO DEL CONDOMINIO
	@PostMapping("/condominium/insert")
	public ResponseEntity<String> insertCondominium(@RequestBody CondominiumDto request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertCondominium(request));
	}
	
	//ENDPOINT DI INSERIMENTO DELLA BOLLETTA
	@PostMapping("/bill/insert")
	public ResponseEntity<String> insertBill(@RequestBody BillRequestDto request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertBill(request));
	}
}
