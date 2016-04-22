package gov.fpds.service;

import gov.fpds.domain.Contract;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.Aggregation;
import io.searchbox.core.search.aggregation.CardinalityAggregation;
import io.searchbox.core.search.aggregation.SumAggregation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;

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

	@Override
	public String getTotalAwards(String startDt, String endDt) {
        String query = "{\n" +
					   " \"query\" : {\n" +
					   "     \"filtered\": {\n" +
					   "         \"filter\": {\n" +
					   "             \"range\": {\n" +
					   "                 \"signeddate\": {\n" +
					   "                         \"gte\": \""+ startDt + "\",\n" +
					   "                         \"lte\": \""+ endDt + "\"\n" +
					   "                     }\n" +
					   "             }\n" +
					   "         }\n" +
					   "     }\n" +
					   " },\n" +
					   " \"aggs\" : {\n" +
					   "     \"totalAwardValue\": {\n" +
					   "         \"sum\" : { \"field\" : \"baseandalloptionsvalue\" }\n" +
					   "     },\n" +
					   "     \"totalContracts\" : {\n" +
					   "         \"cardinality\" : {\n" +
					   "             \"field\" : \"piid\"\n" +
					   "         }\n" +
					   "     }\n" +
					   " }\n" +
					"}";
       
		Search search = new Search.Builder(query)
		                                // multiple index or types can be added.
		                                .addIndex("usaspending")
		                                .addType("contract")
		                                .setSearchType(io.searchbox.params.SearchType.COUNT)
		                                .build();

        String results = null;
		SearchResult result = null;

		try {
			result = jestClient.execute(search);
			Map<String, Class> nameToTypeMap = new HashMap<>(); 
	        nameToTypeMap.put("totalAwardValue", SumAggregation.class);
	        nameToTypeMap.put("totalContracts", CardinalityAggregation.class);
	        
	        List<Aggregation> aggregations = result.getAggregations().getAggregations(nameToTypeMap); 

	        SumAggregation totalAwardsAgg = (SumAggregation) aggregations.get(0);
	        Double totalAwardsVal = totalAwardsAgg.getSum();
	        
	        CardinalityAggregation totalContractsAgg = (CardinalityAggregation)  aggregations.get(1);
	        Long totalContractsVal = totalContractsAgg.getCardinality();
	        
	        JsonObject resultObj = Json.createObjectBuilder()
	        		                .add("total_contracts", totalContractsVal.intValue())
	        		                .add("total_award_value ", totalAwardsVal.doubleValue())
	        		                .build();
	        if(resultObj != null) {
	        	results = resultObj.toString();
	        }
	        

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return results;
	}
	
	

}
