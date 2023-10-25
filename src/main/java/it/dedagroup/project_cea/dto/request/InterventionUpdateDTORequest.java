package it.dedagroup.project_cea.dto.request;

import it.dedagroup.project_cea.model.StatusIntervention;
import it.dedagroup.project_cea.model.TypeOfIntervention;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.Data;
import java.time.LocalDate;
@Data
public class InterventionUpdateDTORequest {
    @Min(value = 1, message = "intervention id must be at least 1")
    private long interventionId;
    @NotNull(message = "intervention date must not be blank")
    private LocalDate interventionDate;
    @NotNull(message = "field isAvailable must not be blank")
    private boolean isAvailable;
    @NotNull(message = "intervention type must not be blank")
    private TypeOfIntervention type;
    @NotNull(message = "intervention status must not be blank")
    private StatusIntervention status;
    @Min(value = 1, message = "technician id must be at least 1")
    private long technicianId;
    @Min(value = 1, message = "apartment id must be at least 1")
    private long apartmentId;
    @Min(value = 1, message = "secretary id must be at least 1")
    private long secretaryId;
}