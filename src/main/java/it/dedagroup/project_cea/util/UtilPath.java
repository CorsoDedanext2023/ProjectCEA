package it.dedagroup.project_cea.util;

public class UtilPath {

    private static final String ADMINISTRATOR_PATH = "/administrator";
    public static final String INSERT_CONDOMINIUM_PATH=ADMINISTRATOR_PATH+"/condominium/insert";
    public static final String INSERT_BILL_PATH=ADMINISTRATOR_PATH+"/bill/insert";
    public static final String CREATE_APARTMENT_PATH=ADMINISTRATOR_PATH+"/create/apartment";
    public static final String GET_CONDOMINIUM_PATH=ADMINISTRATOR_PATH+"/getcondominium";
    public static final String GET_CUSTOMER_PATH=ADMINISTRATOR_PATH+"/getcustomer";
    public static final String GET_SCANS_BY_CONDOMINIUM_ID_PATH=ADMINISTRATOR_PATH+"/getscans/";
    public static final String CREATE_CONDOMINIUM=ADMINISTRATOR_PATH+"/createcondominium";
    public static final String DELETE_ADMINISTRATOR=ADMINISTRATOR_PATH+"/delete";
    public static final String DELETE_CONDOMINIUM=ADMINISTRATOR_PATH+"/condominium/delete";

    private static final String TECHNICIAN_PATH="/technician";
    public static final String GET_SCAN_PATH=TECHNICIAN_PATH+"/getScans";
    public static final String SET_SCAN_PATH=TECHNICIAN_PATH+"/setScan";
    public static final String FIND_BY_ID_PATH=TECHNICIAN_PATH+"/findById";
    public static final String FIND_BY_USER_PATH=TECHNICIAN_PATH+"/findByUser";
    public static final String FIND_BY_INTERVENTIONS_PATH=TECHNICIAN_PATH+"/findByIntervention";
    public static final String FIND_ALL_PATH=TECHNICIAN_PATH+"/findAll";
    public static final String FIND_FREE_PATH=TECHNICIAN_PATH+"/findFree";
    public static final String UPDATE_PATH=TECHNICIAN_PATH+"/update";
    public static final String REBOOK_INTERVENTION_PATH=TECHNICIAN_PATH+"/rebookIntervention";
    public static final String REMOVE_BY_USER_PATH=TECHNICIAN_PATH+"/removeByUsername";
    public static final String REMOVE_BY_ID_PATH=TECHNICIAN_PATH+"/removeById";

    private static final String ALL_PATH="/all";
    public static final String LOGIN_PATH=ALL_PATH+"/login";
    public static final String REGISTER_PATH=ALL_PATH+"/registeruser";

    private static final String CUSTOMER_PATH="/customer";
    public static final String SAVE_PATH=CUSTOMER_PATH+"/save";
    public static final String MODIFY_PATH=CUSTOMER_PATH+"/modify";
    public static final String DELETE_PATH=CUSTOMER_PATH+"/delete/";
    public static final String BOOK_INTERVENTIONS_PATH=CUSTOMER_PATH+"/book/intervention";
    public static final String BILL_PAYMENT_PATH=CUSTOMER_PATH+"/bill/pay";
    public static final String AUTO_SCAN_PATH=CUSTOMER_PATH+"/scan/auto";
    public static final String GET_BILLS_PATH=CUSTOMER_PATH+"/get/bills";
    public static final String GET_CUSTOMER_BY_ID_PATH=CUSTOMER_PATH+"/get/";
    public static final String GET_ALL_CUSTOMER_PATH=CUSTOMER_PATH+"/get/all";
    public static final String GET_CUSTOMER_BY_USERNAME_PATH=CUSTOMER_PATH+"/get/username";
    public static final String GET_CUSTOMER_BY_TAX_CODE_PATH=CUSTOMER_PATH+"/get/taxCode";
    public static final String FIND_ALL_BY_NAME_AND_SURNAME_PATH=CUSTOMER_PATH+"/get/all/name&surname";
    public static final String FIND_CUSTOMER_BY_APARTMENT_ID_PATH=CUSTOMER_PATH+"/get/apartment/";
    public static final String FIND_ALL_APARTMENTS_BY_CUSTOMER_ID = CUSTOMER_PATH+"/all/apartments/";



    private static final String SECRETARY_PATH="/secretary";
    public static final String GET_ALL_BILLS_OF_CONDOMINIUM_PATH=SECRETARY_PATH+"/getAllBillsOfCondominium/";
    public static final String GET_INTERVENTION_LIST_PER_TYPE_PATH=SECRETARY_PATH+"/getInterventionListPerType/";
    public static final String GET_SCANS_PATH=SECRETARY_PATH+"/getScans";
    public static final String REMOTE_SCAN_PATH=SECRETARY_PATH+"/remotescan/";
    public static final String WORKLOAD_PATH=SECRETARY_PATH+"/workload/";
    public static final String ACCEPT_PENDING_INTERVENTION_PATH=SECRETARY_PATH+"/acceptPendingIntervention/";
    public static final String LIST_OF_CONDOMINIUM_OF_TECHNICIAN_INTERVENTIONS_PATH=SECRETARY_PATH+"/listOfCondominiumOfInterventionsOfTechnician/";

    public static final String INTERVENTIONS_OF_TECHNICIAN_BY_DATE_AND_PRIORITY=SECRETARY_PATH+"/interventionsOfTechnicianByDateAndPriority/";
	public static final String GET_ALL_BILLS_OF_CUSTOMER = SECRETARY_PATH + "/getAllBillsOfCustomer/";
    public static final String GET_ALL_FUTURE_INTERVENTIONS_OF_CUSTOMER = SECRETARY_PATH + "/getAllFutureInterventionsOfCustomer/";
    public static final String GET_ALL_UNPAID_BILLS_OF_CUSTOMER = SECRETARY_PATH + "/getAllUnpaidBillsOfCustomer/";
    public static final String GET_SECRETARIES_ASSOCIATED_TO_TECHNICIAN = SECRETARY_PATH + "/getSecretariesOfTechnician/";
    public static final String CHANGE_TECHNICIAN_ASSIGNED_TO_INTERVENTION = SECRETARY_PATH + "/changeTechnicianAssignedToIntervention/";
    public static final String GET_ALL_INTERVENTIONS_SORTED_BY_DATE = SECRETARY_PATH + "/getAllInterventionsSortedByDate";
    public static final String UPDATE_INTERVENTION = SECRETARY_PATH + "/updateIntervention";
    public static final String GET_ALL_TECHNICIANS_AVAILABLE_IN_A_DATE = SECRETARY_PATH + "/getAllTechniciansAvailableInADate/";
    public static final String GET_ALL_INTERVENTIONS_OF_DATE_OF_TECHNICIAN = SECRETARY_PATH + "/interventionsOfDateOfTechnician/";
}
