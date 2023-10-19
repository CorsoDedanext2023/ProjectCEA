package it.dedagroup.project_cea.dto.response;

import lombok.Data;

@Data
public class ApartmentForCondominiumDtoResponse {

    private long id;
    private int unitNumber;
    private int floorNumber;
    private String customerName;
    private String customerSurname;
}
