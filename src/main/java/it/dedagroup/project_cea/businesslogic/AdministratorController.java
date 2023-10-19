package it.dedagroup.project_cea.businesslogic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.request.AdministratorIdDtoRequest;
import it.dedagroup.project_cea.dto.request.BillRequestDto;
import it.dedagroup.project_cea.dto.request.CondominiumDto;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.dto.response.CustomerExtendedInfoDto;
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




    //TODO da inserire dopo quando mark finirà apartment
    public ResponseEntity<String> addApartment(/*AddApartmentDto apartmentDto*/){
        return null;
    }

    
    @PostMapping ("/getcondominium")
    public ResponseEntity<List<CondominiumDtoResponse>> getCondominiumsOfAdministrator(@RequestBody AdministratorIdDtoRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.getCondominiumByAdministratorId(request));
    }
    
    //ENDPOINT PER VISUALIZZARE TUTTI I <CUSTOMER> DI UN DATO CONDOMINIO
    @GetMapping("/getcustomer")
    public ResponseEntity<List<CustomerExtendedInfoDto>> getCustomerOfCondominium(@RequestParam long id){
    	return ResponseEntity.status(HttpStatus.FOUND).body(administratorFacade.getCustomerByCondominiumId(id));
    }

}
