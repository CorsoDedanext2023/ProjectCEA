package it.dedagroup.project_cea.businesslogic;

import it.dedagroup.project_cea.dto.request.AdministratorIdDtoRequest;
import it.dedagroup.project_cea.dto.response.CondominiumDtoResponse;
import it.dedagroup.project_cea.facade.AdministratorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {

    @Autowired
    AdministratorFacade administratorFacade;



    //TODO da inserire dopo quando mark finirà apartment
    public ResponseEntity<String> addApartment(/*AddApartmentDto apartmentDto*/){
        return null;
    }


    @PostMapping ("/getcondominium")
    public ResponseEntity<List<CondominiumDtoResponse>> getCondominiumsOfAdministrator(@RequestBody AdministratorIdDtoRequest request){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(administratorFacade.getCondominiumByAdministratorId(request));
    }
}
