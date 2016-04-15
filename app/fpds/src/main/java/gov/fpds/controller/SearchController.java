package gov.fpds.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import gov.fpds.domain.Contract;
import gov.fpds.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/")
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
	public List<Contract> autoComplete(HttpServletRequest request) {
		String term = request.getParameter("q");
		if(term == null) {
			term = "";
		}
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());

		Search search = new Search.Builder(searchSourceBuilder.toString())
		                                // multiple index or types can be added.
		                                .addIndex("usaspending")
		                                .addType("contract")
		                                .build();

        List<Contract> results = null;
		try {
			results = searchService.searchContracts(term);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}	
	

}
