package gov.fpds.service;

import gov.fpds.domain.Contract;

import java.util.List;

import javax.json.JsonObject;

public interface SearchService {
	//Free-Text Search on all fields
	public List<Contract> searchContracts(String term);
	
	//Get Total Contract value awarded by Date Range
	public String getTotalAwards(String startDt, String endDt);
	
	//Top awards by award value by Date Range
	public List<String> getPrimeAwards(String startDt, String endDt);
}
