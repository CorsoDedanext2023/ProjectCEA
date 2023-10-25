package it.dedagroup.project_cea.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;
//TODO questo DTO può essere trasformato in un pathVariable volendo
//TODO intanto ci inserisco le validation
@Data
public class AdministratorIdDtoRequest {

    @Min(1)
    private long id;
}
