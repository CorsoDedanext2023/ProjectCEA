package it.dedagroup.project_cea.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.dedagroup.project_cea.dto.request.MeterScanDto;
import it.dedagroup.project_cea.dto.response.ApartmentDTOResponse;
import it.dedagroup.project_cea.dto.response.ApartmentScanDTOResponse;
import it.dedagroup.project_cea.model.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import it.dedagroup.project_cea.dto.request.ScanDtoRequest;
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
		scanModel.setApartment(apartmentServ.findById(request.getIdApartment()));
		scanModel.setBills(billList);
		scanModel.setScanDate(request.getScanDate());
		return scanModel;
	}

	public Scan fromScanToDTORequest(MeterScanDto request){
		Scan scan = new Scan();
		Apartment apartment=apartmentServ.findById(request.getIdApartment());
		LocalDate scanDate = LocalDate.parse(request.getScanDate());
		scan.setMcLiter(request.getMcLiter());
		scan.setScanDate(scanDate);
		scan.setApartment(apartment);
		apartment.getScans().add(scan);
		return scan;
	}

	public ApartmentScanDTOResponse fromScanToApartmentScanDTOResponse(Scan scan){
		ApartmentScanDTOResponse response=new ApartmentScanDTOResponse();
		response.setMcLiter(scan.getMcLiter());
		response.setFloorNumber(scan.getApartment().getFloorNumber());
		response.setCustomerName(scan.getApartment().getCustomer().getName());
		response.setCondominium_id(scan.getApartment().getCondominium().getId());
		response.setScanDate(scan.getScanDate());
		return response;
	}

	public List<ApartmentScanDTOResponse> toApartmentScanDtoResposneList (List<Scan> condominiumScans){
		return condominiumScans.stream().map(this::fromScanToApartmentScanDTOResponse).toList();
	}
}
