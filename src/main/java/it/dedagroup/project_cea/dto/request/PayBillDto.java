package it.dedagroup.project_cea.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayBillDto {

	@Min(value=1,
			message="Id cannot be less than 1")
	private long idBill;
	@Min(value=1,
			message="Id cannot be less than 1")
	private long idCustomer;
	@NotNull(message = "Insert a value on payment date field")
	private String paymentDate;
}
