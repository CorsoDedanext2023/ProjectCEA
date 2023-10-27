package it.dedagroup.project_cea.controller;

import static it.dedagroup.project_cea.util.UtilPath.CREATE_APARTMENT_PATH;
import static it.dedagroup.project_cea.util.UtilPath.CREATE_CONDOMINIUM;
import static it.dedagroup.project_cea.util.UtilPath.GET_CONDOMINIUM_PATH;
import static it.dedagroup.project_cea.util.UtilPath.GET_CUSTOMER_PATH;
import static it.dedagroup.project_cea.util.UtilPath.GET_SCANS_BY_CONDOMINIUM_ID_PATH;
import static it.dedagroup.project_cea.util.UtilPath.INSERT_BILL_PATH;
import static it.dedagroup.project_cea.util.UtilPath.INSERT_CONDOMINIUM_PATH;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.dedagroup.project_cea.dto.request.AceaBillRequest;
import it.dedagroup.project_cea.dto.request.AddApartmentDtoRequest;
import it.dedagroup.project_cea.dto.request.AddCondominiumDTORequest;
import it.dedagroup.project_cea.dto.request.AdministratorIdDtoRequest;
import it.dedagroup.project_cea.dto.request.BillDTORequest;
import it.dedagroup.project_cea.dto.request.CondominiumDTORequest;
import it.dedagroup.project_cea.dto.response.ApartmentScanDTOResponse;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.dto.response.CustomerExtendedInfoDTOResponse;
import it.dedagroup.project_cea.facade.AdministratorFacade;
import it.dedagroup.project_cea.model.Apartment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@Tag(name = "Method Administratore", description = "Tutti i metodi che un user <Administrator> può effettuare sul sito")
public class AdministratorController {
	
	@Autowired
	private AdministratorFacade administratorFacade;
	
	//ENDPOINT DI INSERIMENTO DEL CONDOMINIO
	@Operation(summary = "Inserimento <Condominium> nel database.", description = "Questo endpoint consente l'inserimento di un nuovo condominio.")
	@ApiResponses(value = { 
	    @ApiResponse(responseCode = "201", description = "Inserimento del condominio nel database avvenuto. Esempio di risposta: {'message':'Condominio inserito con successo'}"),
	    @ApiResponse(responseCode = "400", description = "Errore nella richiesta. Possono verificarsi errori di validazione dei campi."),
	    @ApiResponse(responseCode = "500", description = "Errore interno del server.")
	})
	@PostMapping(INSERT_CONDOMINIUM_PATH)
	public ResponseEntity<String> insertCondominium(@Valid @RequestBody CondominiumDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertCondominium(request));
	}
	
	//ENDPOINT DI INSERIMENTO DELLA BOLLETTA
	@Operation(summary = "Inserimento <Bill> nel database.", description = "Questo endpoint consente l'inserimento di un nuova bolletta.")
	@ApiResponses(value = { 
	    @ApiResponse(responseCode = "201", description = "Inserimento della bolletta nel database avvenuto. Esempio di risposta: {'message':'Bolletta inserita con successo'}"),
	    @ApiResponse(responseCode = "400", description = "Errore nella richiesta. Possono verificarsi errori di validazione dei campi."),
	    @ApiResponse(responseCode = "500", description = "Errore interno del server.")
	})
	@PostMapping(INSERT_BILL_PATH)
	public ResponseEntity<String> insertBill(@Valid @RequestBody BillDTORequest request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.insertBill(request));
	}


    @PostMapping(CREATE_APARTMENT_PATH)
    @Operation(summary = "Inserimento <Apartment> nel database.", description = "Questo endpoint consente l'inserimento di un nuovo appartamento.")
	@ApiResponses(value = { 
	    @ApiResponse(responseCode = "201", description = "Inserimento dell' appartamento nel database avvenuto. Esempio di risposta: {'message':'Appartamento inserito con successo'}"),
	    @ApiResponse(responseCode = "400", description = "Errore nella richiesta. Possono verificarsi errori di validazione dei campi."),
	    @ApiResponse(responseCode = "500", description = "Errore interno del server.")
	})
    public ResponseEntity<String> addApartment(@Valid @RequestBody AddApartmentDtoRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body("apartment created.");
    }
	//si può cambiare in pathVariable volendo
    @PostMapping (GET_CONDOMINIUM_PATH)
    public ResponseEntity<List<CondominiumDtoResponse>> getCondominiumsOfAdministrator(@Valid @RequestBody AdministratorIdDtoRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.getCondominiumByAdministratorId(request));
    }
    
    //ENDPOINT PER VISUALIZZARE TUTTI I <CUSTOMER> DI UN DATO CONDOMINIO
    @Operation(summary = "Elenco dei clienti di un condominio.", description = "Questo endpoint restituisce l'elenco dei clienti associati a un condominio specifico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Elenco dei clienti ottenuto con successo.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerExtendedInfoDTOResponse.class)))
        ),
        @ApiResponse(responseCode = "400", description = "Condominio non trovato."),
        @ApiResponse(responseCode = "500", description = "Errore interno del server.")
    })
    @Validated
	@GetMapping(GET_CUSTOMER_PATH)
    public ResponseEntity<List<CustomerExtendedInfoDTOResponse>> getCustomerOfCondominium(@RequestParam("id") @Min(value = 1,message = "L'Id_Amministratore deve essere maggiore di 0") long id){
    	return ResponseEntity.status(HttpStatus.FOUND).body(administratorFacade.getCustomerByCondominiumId(id));
    }
    
    //   ENDPOINT DI SUDDIVISIONE BOLLETTE
    @Operation(summary = "Metodo che splitta le bollette",description = "Questo endpoint prende in input una bolletta condominiale e l'id del condominio nella quale bisogna splittarla, se l'id del condominio non coincide va in eccezione e restituisce status code 404, altrimenti restiutisce status code 200 e una stringa che conferma la suddivisione")
   @ApiResponses(value= {
		   @ApiResponse(responseCode = "200",description = "Bollette splittate correttamente",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = String.class))),
		   @ApiResponse(responseCode = "Not Found(404)",description = "Nessun condominio co questo id",content = @Content(mediaType = MediaType.ALL_VALUE))
   })
    @PostMapping("/billSplitter/{idCondominium}")
    public ResponseEntity<String> billSplitter(@PathVariable long idCondominium,@RequestBody AceaBillRequest request){
        return ResponseEntity.ok(administratorFacade.billSplitter(idCondominium, request));
    }

    @Operation(summary = "Elenco dele bollette di un condominio.", description = "Questo endpoint restituisce l'elenco delle bollette associati a un condominio specifico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Elenco delle bollette ottenuto con successo.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerExtendedInfoDTOResponse.class)))
        ),
        @ApiResponse(responseCode = "400", description = "Condominio non trovato."),
        @ApiResponse(responseCode = "500", description = "Errore interno del server.")
    })
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
