package it.dedagroup.project_cea.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
	
	private String message;
	private LocalDateTime date;

}
