package gov.fpds.controller;

import gov.fpds.domain.Contract;
import gov.fpds.domain.SearchResponse;
import gov.fpds.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
	
	@Autowired
	JestClient jestClient;
	
	@Autowired
	SearchService searchService;
	
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@RequestMapping("/total")
	public Integer totalDocuments() {
		String query = "{ \"query\" : {\"match_all\": {} }}";

		Search search = new Search.Builder(query)
		                                .addIndex("usaspending")
		                                .addType("contract")
		                                .build();
		SearchResult result = null;

		try {
			 result = jestClient.execute(search);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.getTotal();
	}
	
	@RequestMapping("/search")
	public String search(HttpServletRequest request) {
		String term = request.getParameter("q");
		if(term == null) {
			term = "";
		}

        String results = null;
		try {
			results = searchService.searchContracts(term);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}	
	
	@RequestMapping("/autocomplete")
	public List<String> autoComplete(HttpServletRequest request) {
		String term = request.getParameter("q");
		if(term == null) {
			term = "";
		}

        List<String> results = null;
		try {
			results = searchService.autoSuggest(term);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	@RequestMapping("/total_awards")
	public String getTotalAwards(HttpServletRequest request) {
		String startDt = request.getParameter("start_date");
		String endDt = request.getParameter("end_date");
		if(endDt == null) {
			LocalDate now = LocalDate.now();
			endDt = now.format(dateFormatter);
		}
		String results = searchService.getTotalAwards(startDt, endDt);
		return results;
	}			

	
	@RequestMapping("/prime_contracts")
	public String getPrimeContracts(HttpServletRequest request) {
		String startDt = request.getParameter("start_date");
		String endDt = request.getParameter("end_date");
		if(endDt == null) {
			LocalDate now = LocalDate.now();
			endDt = now.format(dateFormatter);
		}
		String results = searchService.getPrimeAwards(startDt, endDt);
		return results;
	}	

	@RequestMapping("/results")
	public SearchResponse getSearchResults(HttpServletRequest request) {
		String term = request.getParameter("q");
		String from = request.getParameter("from");
		int frm = 0;
		if(from != null) {
			frm = Integer.parseInt(from);
		}
		String size = request.getParameter("size");
		int sze = 10;
		if(size != null) {
			sze = Integer.parseInt(size);
		}		
		
		if(term == null) {
			term = "";
		}
		SearchResponse results = searchService.getSearchResults(term, frm, sze);
		return results;
	}		
	
	@RequestMapping("/piidcontracts")
	public List<Contract> getContractsByPiid(HttpServletRequest request) {
		String piid = request.getParameter("piid");
			
		if(piid == null) {
			piid = "";
		}
		List<Contract> results = searchService.getTransactionsByPiid(piid);
		return results;
	}
	
	@RequestMapping("/idvcontracts")
	public Map<String, List<Contract>> getContractsByIdv(HttpServletRequest request) {
		String idv = request.getParameter("idv");
			
		if(idv == null) {
			idv = "";
		}
		Map<String, List<Contract>> results = searchService.getTransactionsByIdvid(idv);
		return results;
	}
	
	@RequestMapping("/results/analytics")
	public String getResultsAnalytics(HttpServletRequest request) {
		String term = request.getParameter("q");
		String startDt = request.getParameter("start_date");
		if(startDt == null) {
			LocalDate begin = LocalDate.of(2001, 1, 1);
			startDt = begin.format(dateFormatter);
		}		
		String endDt = request.getParameter("end_date");
		if(endDt == null) {
			LocalDate now = LocalDate.now();
			endDt = now.format(dateFormatter);
		}
		String results = searchService.getResultsAnalytics(term, startDt, endDt);
		return results;
	}
}
