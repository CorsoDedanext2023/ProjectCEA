package it.dedagroup.project_cea.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class CondominiumForAdministratorDtoResponse {

    private long id;
    private String address;
    private String administratorName;
    private List<ApartmentForCondominiumDtoResponse> apartments;
}
