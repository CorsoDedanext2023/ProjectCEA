package it.dedagroup.project_cea.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class AddApartmentForAddCondominiumDTORequest {

    @NotBlank(message = "Il numero dell' appartamento non può essere lasciato vuoto")
    private int unitNumber;
    @NotBlank(message = "Il numero del piano non può essere lasciato vuoto")
    private int floorNumber;
    private long id_customer;



}
