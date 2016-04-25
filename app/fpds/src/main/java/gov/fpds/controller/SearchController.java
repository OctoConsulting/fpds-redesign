package gov.fpds.controller;

import gov.fpds.domain.Contract;
import gov.fpds.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {
	@Autowired
	ElasticsearchTemplate elasticSearchTemplate;
	
	@Autowired
	JestClient jestClient;
	
	@Autowired
	SearchService searchService;
	
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@RequestMapping("/total")
	public Integer totalDocuments() {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());

		Search search = new Search.Builder(searchSourceBuilder.toString())
		                                // multiple index or types can be added.
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
	public String autoComplete(HttpServletRequest request) {
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
}
