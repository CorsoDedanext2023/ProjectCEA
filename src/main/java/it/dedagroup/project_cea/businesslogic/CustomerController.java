package it.dedagroup.project_cea.businesslogic;

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
import it.dedagroup.project_cea.dto.request.LoginDTORequest;
import it.dedagroup.project_cea.dto.request.CustomerNameSurnameDtoRequest;
import it.dedagroup.project_cea.dto.response.CustomerDtoResponse;
import it.dedagroup.project_cea.dto.request.AddCustomerDtoRequest;
import it.dedagroup.project_cea.dto.request.BookInterventionDto;
import it.dedagroup.project_cea.dto.request.EditCustomerDto;
import it.dedagroup.project_cea.dto.request.MeterScanDto;
import it.dedagroup.project_cea.dto.request.PayBillDto;
import it.dedagroup.project_cea.facade.CustomerFacade;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Scan;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerFacade customerFacade;

	@PostMapping("/save")
	public ResponseEntity<Void> addCustomer(@Valid @RequestBody AddCustomerDtoRequest request){
		customerFacade.saveCustomer(request);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@PostMapping("/modify")
	public ResponseEntity<Void> modifyCustomer(@Valid @RequestBody EditCustomerDto request){
		customerFacade.modifyCustomer(request);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@PostMapping("/delete/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable long id){
		customerFacade.deleteCustomer(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	@PostMapping("/book/intervention")
	public ResponseEntity<Intervention> bookIntervention(@Valid @RequestBody BookInterventionDto request){
		return ResponseEntity.ok(customerFacade.bookIntervention(request));
	}
	
	@GetMapping("/bill/pay")
	public ResponseEntity<Bill> payBill(@Valid @RequestBody PayBillDto request){
		return ResponseEntity.ok(customerFacade.payBill(request));
	}
	
	@GetMapping("/scan/auto")
	public ResponseEntity<Scan> autoScan(@Valid @RequestBody MeterScanDto request){
		return ResponseEntity.ok(customerFacade.autoScan(request));
	}
	
	@GetMapping("/get/bills")
	public ResponseEntity<List<Bill>> getBills(@RequestParam("id") long id_customer){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.getBills(id_customer));
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<CustomerDtoResponse> findCustomerById(@PathVariable("id") long id_customer) {
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerById(id_customer));
	}
	@GetMapping("/get/all")
	public ResponseEntity<List<CustomerDtoResponse>> findAllCustomer(){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findAllCustomer());
	}
	@PostMapping("/login")
	public ResponseEntity<CustomerDtoResponse> findCustomerByUsernameAndPassword(@RequestBody @Valid LoginDTORequest request){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByUsernameAndPassword(request));
	}
	@GetMapping("/get/username")
	public ResponseEntity<CustomerDtoResponse> findCustomerByUsername(@RequestParam("username") String username){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByUsername(username));
	}
	@GetMapping("/get/taxCode")
	public ResponseEntity<CustomerDtoResponse> findCustomerByTax_Code(@RequestParam("tax_code") String taxCode){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByTax_Code(taxCode));
	}
	@GetMapping("/get/all/name&surname")
	public ResponseEntity<List<CustomerDtoResponse>> findAllCustomerByNameAndSurname(@RequestBody @Valid CustomerNameSurnameDtoRequest request){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findAllCustomerByNameAndSurname(request));
	}
	@GetMapping("/get/apartment/id")
	public ResponseEntity<CustomerDtoResponse> findCustomerByApartments_Id(@PathVariable("id") long id_apartment){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByApartments_Id(id_apartment));
	}
}
