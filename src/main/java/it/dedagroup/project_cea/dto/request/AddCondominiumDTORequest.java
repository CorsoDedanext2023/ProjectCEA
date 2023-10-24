package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

//TODO implementare validation
@Data
public class AddCondominiumDTORequest {

    @NotEmpty(message = "L'indirizzo del condominio non può essere lasciato vuoto")
    private String address;
    @NotNull(message = "L'Amministratore_Id non può essere lasciato vuoto")
    @Min(value = 1,message = "L'Amministratore_Id deve essere maggiore di 1")
    private long administrator_id;
    @NotNull(message = "La lista di appartamenti deve essere presente e valida")
    private List<AddApartmentForAddCondominiumDTORequest> apartmentList;




}
