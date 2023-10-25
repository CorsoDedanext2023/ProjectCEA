package it.dedagroup.project_cea.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ApartmentScanDTOResponse {

    private int floorNumber;
    private String customerName;
    private double mcLiter;
    private long condominium_id;
    private LocalDate scanDate;
}
