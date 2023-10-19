package it.dedagroup.project_cea.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.dedagroup.project_cea.dto.request.ScanDtoRequest;
import it.dedagroup.project_cea.dto.response.BillDTOResponse;
import it.dedagroup.project_cea.dto.response.ScanDTOResponse;
import it.dedagroup.project_cea.model.Bill;
import it.dedagroup.project_cea.model.Scan;
import it.dedagroup.project_cea.service.def.ApartmentServiceDef;
import it.dedagroup.project_cea.service.def.BillServiceDef;

@Component
public class ScanMapper {
	
	@Autowired
	BillServiceDef billServ;
	
	@Autowired
	ApartmentServiceDef apartmentServ;
	
	@Autowired
	BillMapper billMap;
	
	public ScanDTOResponse toScanDTOResponse(Scan sc) {
		ScanDTOResponse scDTOResp = new ScanDTOResponse();
		scDTOResp.setMcLiter(sc.getMcLiter());
		scDTOResp.setCondominiumAddress(sc.getApartment().getCondominium().getAddress());
		List<Bill> billsOfCondominium = billServ.findAll().stream().filter(b -> b.getScan().getId() == sc.getId()).toList();
		scDTOResp.setBills(billMap.toBillDTOResponseList(billsOfCondominium));
		return scDTOResp;
	}
	
	public List<ScanDTOResponse> toScanDTOResponseList(List<Scan> scansList){
		return scansList.stream().map(this::toScanDTOResponse).toList();
	}
	
	public Scan toScanFromDtoRequest(ScanDtoRequest request) {
		Scan scanModel = new Scan();
		List<Bill> billList = new ArrayList<>();
		scanModel.setMcLiter(request.getMcLiter());
		scanModel.setAvailable(true);
		scanModel.setApartment(apartmentServ.findApartmentByInterventionsId(request.getApartmentId()));
		scanModel.setBills(billList);
		return scanModel;
	}
}
