package it.dedagroup.project_cea.mapper;

import it.dedagroup.project_cea.dto.response.InterventionDTOResponse;
import it.dedagroup.project_cea.dto.response.SecretaryDTOResponse;
import it.dedagroup.project_cea.model.Intervention;
import it.dedagroup.project_cea.model.Secretary;
import it.dedagroup.project_cea.model.Technician;
import it.dedagroup.project_cea.service.def.InterventionServiceDef;
import it.dedagroup.project_cea.service.def.TechnicianServiceDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class SecretaryMapper {
    @Autowired
    TechnicianMapper techMap;

    @Autowired
    InterventionServiceDef interServ;

    @Autowired
    InterventionMapper intMap;

    public SecretaryDTOResponse toSecretaryDTOResponse(Secretary s){
        SecretaryDTOResponse secDTOResp = new SecretaryDTOResponse();
        secDTOResp.setName(s.getName());
        secDTOResp.setSurname(s.getSurname());

        List<InterventionDTOResponse> intervensions = intMap.toInterventionDTOResponseList(s.getIntervention());
        secDTOResp.setIntervensions(intervensions);
        return secDTOResp;
    }

    public List<SecretaryDTOResponse> toSecretaryDTOResponseList(List<Secretary> secretaries){
        return secretaries.stream().map(this::toSecretaryDTOResponse).toList();
    }
}
