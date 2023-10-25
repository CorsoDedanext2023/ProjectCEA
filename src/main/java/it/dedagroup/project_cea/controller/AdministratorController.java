package it.dedagroup.project_cea.controller;



import it.dedagroup.project_cea.dto.request.*;
import it.dedagroup.project_cea.dto.response.ApartmentScanDTOResponse;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.dto.response.CustomerExtendedInfoDTOResponse;
import it.dedagroup.project_cea.facade.AdministratorFacade;
import it.dedagroup.project_cea.model.Apartment;
import it.dedagroup.project_cea.model.Condominium;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static it.dedagroup.project_cea.util.UtilPath.*;

@RestController
@Validated
public class AdministratorController {
	
	@Autowired
	private AdministratorFacade administratorFacade;
	
	//ENDPOINT DI INSERIMENTO DEL CONDOMINIO
	@PostMapping(INSERT_CONDOMINIUM_PATH)
	public ResponseEntity<String> insertCondominium(@Valid @RequestBody CondominiumDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertCondominium(request));
	}
	
	//ENDPOINT DI INSERIMENTO DELLA BOLLETTA
	@PostMapping(INSERT_BILL_PATH)
	public ResponseEntity<String> insertBill(@Valid @RequestBody BillDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertBill(request));
	}


    @PostMapping(CREATE_APARTMENT_PATH)
    public ResponseEntity<String> addApartment(@Valid @RequestBody AddApartmentDtoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body("apartment created.");
    }
	//si può cambiare in pathVariable volendo
    @PostMapping (GET_CONDOMINIUM_PATH)
    public ResponseEntity<List<CondominiumDtoResponse>> getCondominiumsOfAdministrator(@Valid @RequestBody AdministratorIdDtoRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.getCondominiumByAdministratorId(request));
    }
    
    //ENDPOINT PER VISUALIZZARE TUTTI I <CUSTOMER> DI UN DATO CONDOMINIO
    @Validated
	@GetMapping(GET_CUSTOMER_PATH)
    public ResponseEntity<List<CustomerExtendedInfoDTOResponse>> getCustomerOfCondominium(@RequestParam("id") @Min(value = 1,message = "L'Id_Amministratore deve essere maggiore di 0") long id){
    	return ResponseEntity.status(HttpStatus.FOUND).body(administratorFacade.getCustomerByCondominiumId(id));
    }


	@GetMapping(GET_SCANS_BY_CONDOMINIUM_ID_PATH+"{id}")
	public ResponseEntity<List<ApartmentScanDTOResponse>> findAllScanByCondominiumId(@PathVariable("id") @Min(value = 1,message = "L'Id_Condominio deve essere maggiore di 0") long id){
		return ResponseEntity.status(HttpStatus.FOUND).body(administratorFacade.findAllScanByCondominiumId(id));
	}
	//inserire in util path
	//TODO da controllare se si necessita realmente di questo endpoint
	@PostMapping("/assign/apartment")
	public ResponseEntity<String> assignApartment(Apartment apartment){
		return null;
	}
	//TODO da sostituire la responseEntity con un dto del customer con l'id del nuovo appartamento
	@PostMapping(CREATE_CONDOMINIUM)
	public ResponseEntity<String> CreateCondominium(@Valid @RequestBody AddCondominiumDTORequest request){
		administratorFacade.createCondominium(request);
		return ResponseEntity.status(HttpStatus.CREATED).body("Condominio Aggiunto con successo.");
	}

}
