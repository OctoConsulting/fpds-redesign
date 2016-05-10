package gov.fpds.service;

import gov.fpds.domain.Contract;
import gov.fpds.domain.SearchResponse;

import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

public interface SearchService {
	//Free-Text Search on all fields
	public String searchContracts(String term);
	
	//Get Total Contract value awarded by Date Range
	public String getTotalAwards(String startDt, String endDt);
	
	//Top awards by award value by Date Range
	public String getPrimeAwards(String startDt, String endDt);
	
	//Search Results
	public SearchResponse getSearchResults(String searchTerm, int from, int size);
	
	//Transactions by piid
	public List<Contract> getTransactionsByPiid(String piid);
	
	//Transactions by idvid
	public Map<String, List<Contract>> getTransactionsByIdvid(String idvid);
	
	//Search Results Analytics
	public String getResultsAnalytics(String searchTerm, String startDt, String endDt);
	
	//Revised Autocomplete
	public List<String> autoSuggest(String searchTerm);
}
