package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScanDtoRequest {
    @NotNull(message = "Liter cannot be empty")
    private double mcLiter;
    @NotNull(message = "Il campo data non può essere lasciato in bianco")
    private LocalDate scanDate;
    @NotNull(message = "Il campo apartmentId non può essere lasciato in bianco")
    private long idApartment;
    @NotNull(message = "Id tecnico non può essere lasciato bianco")
    private long idTechnician;
    @NotNull(message = "Id intervento non può essere lasciato bianco")
    private long idIntervention;

}
