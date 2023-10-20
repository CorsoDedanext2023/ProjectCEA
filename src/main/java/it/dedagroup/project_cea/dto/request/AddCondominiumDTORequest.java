package it.dedagroup.project_cea.dto.request;

import lombok.Data;

import java.util.List;

//TODO implementare validation
@Data
public class AddCondominiumDTORequest {

    private String address;
    private long administrator_id;
    private List<AddApartmentForAddCondominiumDTORequest> apartmentList;




}
