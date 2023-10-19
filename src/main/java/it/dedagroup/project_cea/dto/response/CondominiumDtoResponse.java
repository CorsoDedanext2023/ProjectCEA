package it.dedagroup.project_cea.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CondominiumDtoResponse {

    private String address;
    private String admName;
    private String admSurname;
    List<ApartmentForCondominiumDtoResponse> apartments;
}
