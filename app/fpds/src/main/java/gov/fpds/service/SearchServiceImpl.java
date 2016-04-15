package gov.fpds.service;

import gov.fpds.domain.Contract;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	JestClient jestClient;

	@Override
	public List<Contract> searchContracts(String term) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.queryStringQuery(term));

		Search search = new Search.Builder(searchSourceBuilder.toString())
		                                .addIndex("usaspending")
		                                .addType("contract")
		                                .build();
		SearchResult result = null;
        List<Contract> retVal = null;
		try {
			 result = jestClient.execute(search);
			 List<SearchResult.Hit<Contract, Void>> hits = result.getHits(Contract.class);
			 retVal = hits.stream().map(hit -> hit.source)//.forEach(System.out::println);
			 .collect(Collectors.toList());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return retVal;

	}

}
