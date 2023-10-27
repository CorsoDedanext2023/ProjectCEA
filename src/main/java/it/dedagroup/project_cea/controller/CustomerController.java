package it.dedagroup.project_cea.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.dedagroup.project_cea.dto.request.LoginDTORequest;
import it.dedagroup.project_cea.dto.request.CustomerNameSurnameDtoRequest;

import it.dedagroup.project_cea.dto.request.*;
import it.dedagroup.project_cea.dto.response.CustomerDtoResponse;
import it.dedagroup.project_cea.facade.CustomerFacade;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static it.dedagroup.project_cea.util.UtilPath.*;

@RestController
public class CustomerController {
	//TODO validate i request param e i pathvariable
	@Autowired
	CustomerFacade customerFacade;

	
	/**
	 * Salva un nuovo cliente.
	 *
	 * @param si aspetta una request Oggetto di tipo AddCustomerDtoRequest contenente i dati del cliente da salvare.
	 * @return ritorna una ResponseEntity con stato HTTP ACCEPTED.
	 */
	@PostMapping(SAVE_PATH)
	public ResponseEntity<Void> addCustomer(@Valid @RequestBody AddCustomerDtoRequest request){
		customerFacade.saveCustomer(request);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	/**
	 * Modifica un cliente che esiste già.
	 *
	 * @param request Oggetto di tipo EditCustomerDto contenente i dati del cliente da modificare.
	 * @return ResponseEntity con stato HTTP ACCEPTED.
	 */
	@PostMapping(MODIFY_PATH)
	public ResponseEntity<Void> modifyCustomer(@Valid @RequestBody EditCustomerDto request){
		customerFacade.modifyCustomer(request);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	
	/**
	 * Elimina un cliente esistente tramite il suo id, che viene estratto dalla variabile del percorso.
	 *
	 * @param id del cliente da cancellare 
	 * @return ResponseEntity con stato HTTP ACCEPTED.
	 */
	@PostMapping(DELETE_PATH+"{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable long id){
		customerFacade.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	
	/**
	 * Prenota un intervento con tutte le informazioni.
	 *
	 * @param request Oggetto di tipo BookInterventionDto che conterrà le informazioni per prenotare un intervento, come ad esempio "LocalDate interventionDate".
	 * @return ResponseEntity con oggetto Intervention.
	 */
	@PostMapping(BOOK_INTERVENTIONS_PATH)
	public ResponseEntity<Intervention> bookIntervention(@Valid @RequestBody BookInterventionDto request){
		return ResponseEntity.ok(customerFacade.bookIntervention(request));
	}
	
	

	/**
	 * Metodo per il pagamento della bolletta.
	 *
	 * @param Si aspetta un corpo della richiesta JSON di tipo PayBillDto
	 * @return ResponseEntity con oggetto Bill.
	 */
	@GetMapping(BILL_PAYMENT_PATH)
	public ResponseEntity<Bill> payBill(@Valid @RequestBody PayBillDto request){
		return ResponseEntity.ok(customerFacade.payBill(request));
	}
	
	
	/**
	 * Metodo per avviare una scansione automatica e restituire un oggetto Scan come risposta.
	 *
	 * @param Si aspetta un corpo della richiesta JSON di tipo MeterScannDto
	 * @return ResponseEntity con oggetto Scan.
	 */
	@GetMapping(AUTO_SCAN_PATH)
	public ResponseEntity<Scan> autoScan(@Valid @RequestBody MeterScanDto request){
		return ResponseEntity.ok(customerFacade.autoScan(request));
	}
	
	
	/**
	 * Prende una lista di bollette di un cliente tramite id .
	 * @param si aspetta un id del cliente 
	 * @return Ritorna una list di oggetti bollette.
	 */
	@GetMapping(GET_BILLS_PATH)
	public ResponseEntity<List<Bill>> getBills(@RequestParam("id") long id_customer){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.getBills(id_customer));
	}
	
	/**
	 * Prende un cliente tramite id.
	 *Questo metodo accetta un ID cliente come parametro e restituisce un oggetto di tipo CustomerDtoResponse che rappresenta il cliente corrispondente.
	 * @param id cliente
	 * @return Restituisce una risposta contenente un oggetto `CustomerDtoResponse`.
	 */
	@GetMapping(GET_CUSTOMER_BY_ID_PATH+"{id}")
	public ResponseEntity<CustomerDtoResponse> findCustomerById(@PathVariable("id") long id_customer) {
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerById(id_customer));
	}
	
	
	/**
	 * Prende tutti i clienti registrati senza richiedere parametri
	 * @return ResponseEntity con stato HTTP FOUND contenente una lista di CustomerDtoResponse che corrisponde ai clienti registrati
	 */
	@GetMapping(GET_ALL_CUSTOMER_PATH)
	public ResponseEntity<List<CustomerDtoResponse>> findAllCustomer(){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findAllCustomer());
	}
	
	
	/**
	 * Gestisce la login di un cliente
	 * @param prende un corpo della richiesta JSON di tipo LoginDTORequest
	 * @return ResponseEntity con stato HTTP FOUND contenente una lista di CustomerDtoResponse
	 */
	@PostMapping("/login")
	public ResponseEntity<CustomerDtoResponse> findCustomerByUsernameAndPassword(@RequestBody @Valid LoginDTORequest request){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByUsernameAndPassword(request));
	}
	
	/**
	 *Recupera un cliente tramite il loro username, fornito come parametro di query
	 * @param username cliente 
	 * @return risposta contenente un oggetto `CustomerDtoResponse`.
	 */
	@GetMapping(GET_CUSTOMER_BY_USERNAME_PATH)
	public ResponseEntity<CustomerDtoResponse> findCustomerByUsername(@RequestParam("username") String username){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByUsername(username));
	}
	
	
	/**
	 *Recupera un cliente tramite il loro codice fiscale, fornito come parametro di query
	 * @param tax code 
	 * @return risposta contenente un oggetto `CustomerDtoResponse`.
	 */
	@GetMapping(GET_CUSTOMER_BY_TAX_CODE_PATH)
	public ResponseEntity<CustomerDtoResponse> findCustomerByTax_Code(@RequestParam("tax_code") String taxCode){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByTax_Code(taxCode));
	}
	
	
	/**
	 *Metodo che trova tutti i clienti tramite nome e cognome
	 * @param prende un corpo della richiesta JSON di tipo 'CustomerNameSurnameDtoRequest'
	 * @return risposta contenente una lista `CustomerDtoResponse`.
	 */
	@GetMapping(FIND_ALL_BY_NAME_AND_SURNAME_PATH)
	public ResponseEntity<List<CustomerDtoResponse>> findAllCustomerByNameAndSurname(@RequestBody @Valid CustomerNameSurnameDtoRequest request){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findAllCustomerByNameAndSurname(request));
	}
	
	
	/**
	 *Metodo che trova tutti i clienti tramite id dell appartamento
	 * @param prende id appartamento tramite query
	 * @return risposta contenente una lista `CustomerDtoResponse`.
	 */
	@GetMapping(FIND_CUSTOMER_BY_APARTMENT_ID_PATH+"{id}")
	public ResponseEntity<CustomerDtoResponse> findCustomerByApartments_Id(@PathVariable("id") long id_apartment){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByApartments_Id(id_apartment));
	}
}
