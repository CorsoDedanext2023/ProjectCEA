package it.dedagroup.project_cea.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.model.Bill;

@Component
public class BillMapper {
	
	public BillDTOResponse toBillDTOResponse(Bill b) {
		BillDTOResponse billDTOResp = new BillDTOResponse();
		billDTOResp.setCost(b.getCost());
		billDTOResp.setDeliveringDay(b.getDeliveringDay());
		billDTOResp.setPaymentDay(b.getPaymentDay());
		billDTOResp.setName(b.getScan().getApartment().getCustomer().getName());
		billDTOResp.setSurname(b.getScan().getApartment().getCustomer().getSurname());
		billDTOResp.setUnitNumber(b.getScan().getApartment().getUnitNumber());
		billDTOResp.setFloorNumber(b.getScan().getApartment().getFloorNumber());
		return billDTOResp;
	}
	
	public List<BillDTOResponse> toBillDTOResponseList(List<Bill> bills){
		return bills.stream().map(this::toBillDTOResponse).toList();
	}
}
