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
import it.dedagroup.project_cea.dto.request.NameSurnameRequest;
import it.dedagroup.project_cea.dto.response.CustomerDto;
import it.dedagroup.project_cea.facade.CustomerFacade;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerFacade customerFacade;
	
	@PostMapping("/get/{id}")
	public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("id") long id_customer) {
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerById(id_customer));
	}
	@GetMapping("/get/all")
	public ResponseEntity<List<CustomerDto>> findAllCustomer(){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findAllCustomer());
	}
	@PostMapping("/login")
	public ResponseEntity<CustomerDto> findCustomerByUsernameAndPassword(@RequestBody @Valid LoginDTORequest request){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByUsernameAndPassword(request));
	}
	@PostMapping("/get/username")
	public ResponseEntity<CustomerDto> findCustomerByUsername(@RequestParam("username") String username){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByUsername(username));
	}
	@PostMapping("/get/taxCode")
	public ResponseEntity<CustomerDto> findCustomerByTax_Code(@RequestParam("tax_code") String taxCode){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByTax_Code(taxCode));
	}
	@PostMapping("/get/all/name&surname")
	public ResponseEntity<List<CustomerDto>> findAllCustomerByNameAndSurname(@RequestBody @Valid NameSurnameRequest request){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findAllCustomerByNameAndSurname(request));
	}
	@PostMapping("/get/apartment/id")
	public ResponseEntity<CustomerDto> findCustomerByApartments_Id(@PathVariable("id") long id_apartment){
		return ResponseEntity.status(HttpStatus.FOUND).body(customerFacade.findCustomerByApartments_Id(id_apartment));
	}
}
