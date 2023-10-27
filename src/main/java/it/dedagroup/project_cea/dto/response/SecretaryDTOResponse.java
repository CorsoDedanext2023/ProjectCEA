package it.dedagroup.project_cea.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SecretaryDTOResponse {

    public String name;
    public String surname;
    public List<InterventionDTOResponse> intervensions;
}
