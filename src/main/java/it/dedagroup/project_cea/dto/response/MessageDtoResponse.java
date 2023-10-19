package it.dedagroup.project_cea.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDtoResponse {
	private List<String> message = new ArrayList<>();
	private int codice;
	private Object request;
	private LocalDateTime data;
	public MessageDtoResponse(List<String> message, int codice) {
		this.message = message;
		this.codice = codice;
		this.data = LocalDateTime.now();
	}
	
	public MessageDtoResponse(String errore, int codice, Object request, LocalDateTime data) {
		message.add(errore);
		this.codice = codice;
		this.request = request;
		this.data = data;
	}
	
	
}
