package it.dedagroup.project_cea.businesslogic;

import java.util.List;

import it.dedagroup.project_cea.dto.request.AddApartmentDtoRequest;
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
import it.dedagroup.project_cea.dto.request.BillDTORequest;
import it.dedagroup.project_cea.dto.request.CondominiumDTORequest;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.dto.response.CustomerExtendedInfoDTOResponse;
import it.dedagroup.project_cea.facade.AdministratorFacade;
import static it.dedagroup.project_cea.util.UtilPath.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private AdministratorFacade administratorFacade;
	
	//ENDPOINT DI INSERIMENTO DEL CONDOMINIO
	@PostMapping(INSERT_CONDOMINIUM_PATH)
	public ResponseEntity<String> insertCondominium(@RequestBody CondominiumDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertCondominium(request));
	}
	
	//ENDPOINT DI INSERIMENTO DELLA BOLLETTA
	@PostMapping(INSERT_BILL_PATH)
	public ResponseEntity<String> insertBill(@RequestBody BillDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertBill(request));
	}


    @PostMapping(CREATE_APARTMENT_PATH)
    public ResponseEntity<String> addApartment(@RequestBody AddApartmentDtoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body("apartment created.");
    }

    @PostMapping (GET_CONDOMINIUM_PATH)
    public ResponseEntity<List<CondominiumDtoResponse>> getCondominiumsOfAdministrator(@RequestBody AdministratorIdDtoRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.getCondominiumByAdministratorId(request));
    }
    
    //ENDPOINT PER VISUALIZZARE TUTTI I <CUSTOMER> DI UN DATO CONDOMINIO
    @GetMapping(GET_CUSTOMER_PATH)
    public ResponseEntity<List<CustomerExtendedInfoDTOResponse>> getCustomerOfCondominium(@RequestParam long id){
    	return ResponseEntity.status(HttpStatus.FOUND).body(administratorFacade.getCustomerByCondominiumId(id));
    }

}
