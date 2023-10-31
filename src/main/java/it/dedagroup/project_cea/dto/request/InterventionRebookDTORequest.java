package it.dedagroup.project_cea.dto.request;

import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aspectj.lang.annotation.After;

import java.time.LocalDate;
@Data
public class InterventionRebookDTORequest {
    @Min(value = 1, message = "intervention id must be at least 1")
    private long idIntervention;
    @NotNull(message = "intervention date must not be blank")
    private LocalDate interventionDate;
    @NotNull(message = "postponed intervention date must not be blank")
    @Future(message = "postponed intervention date must be in the future")
    private LocalDate postponedDate;
    @NotNull(message = "intervention type must not be blank")
    private TypeOfIntervention type;
    @Min(value = 1, message = "technician id must be at least 1")
    private long idTechnician;
    @Min(value = 1, message = "apartment id must be at least 1")
    private long idApartment;
}
