package it.dedagroup.project_cea.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AddApartmentForAddCondominiumDTORequest {

    private int unitNumber;
    private int floorNumber;
    private long id_customer;



}
