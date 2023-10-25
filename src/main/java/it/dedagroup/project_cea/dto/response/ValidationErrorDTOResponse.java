package it.dedagroup.project_cea.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ValidationErrorDTOResponse {

    private List<ViolationDTOResponse> violations =new ArrayList<>();


}
