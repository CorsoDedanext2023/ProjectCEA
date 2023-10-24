package it.dedagroup.project_cea.dto.response;

import lombok.Data;

@Data
public class ViolationDTOResponse {

    private final String fieldName;
    private final String message;
    private final String invalid_Data;
}
