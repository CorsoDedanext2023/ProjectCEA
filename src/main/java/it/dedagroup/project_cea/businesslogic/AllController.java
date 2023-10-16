package it.dedagroup.project_cea.businesslogic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dedagroup.project_cea.dto.request.LoginDTORequest;
import it.dedagroup.project_cea.dto.response.LoginDTOResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/all")
public class AllController {
	
	public ResponseEntity<LoginDTOResponse> login(@Valid @RequestBody LoginDTORequest request){
		return null;
	}
	

}
