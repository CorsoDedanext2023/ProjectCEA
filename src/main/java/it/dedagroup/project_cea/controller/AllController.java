package it.dedagroup.project_cea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.request.LoginDTORequest;
import it.dedagroup.project_cea.dto.request.RegisterUserDto;
import it.dedagroup.project_cea.dto.response.LoginDTOResponse;
import it.dedagroup.project_cea.facade.AllFacade;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/all")
public class AllController {
	
	@Autowired
	private AllFacade allFacade;
	
	public ResponseEntity<LoginDTOResponse> login(@Valid @RequestBody LoginDTORequest request){
		return null;
	}
	
	@PostMapping("/register/user")
	public ResponseEntity<String> registerAccount(@Valid @RequestBody RegisterUserDto request){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allFacade.registerUser(request));
	}

	

}
