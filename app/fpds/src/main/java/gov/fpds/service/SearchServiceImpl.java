package gov.fpds.service;

import gov.fpds.domain.Contract;
import gov.fpds.domain.SearchResponse;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.search.aggregation.Aggregation;
import io.searchbox.core.search.aggregation.CardinalityAggregation;
import io.searchbox.core.search.aggregation.SumAggregation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	JestClient jestClient;
	
	
	private final Function<Hit<Contract, Void>, String> autoCompleteConverter = h -> {
																					   Map<String, List<String>> highlights = h.highlight;
																					   String retVal = "";
																					   if(highlights != null && highlights.size() > 0) {
																						    Iterator<Entry<String, List<String>>> it = highlights.entrySet().iterator();
																						    JsonObject obj = null;
																						    if (it.hasNext()) {
																						        Map.Entry<String, List<String>> pair = it.next();
																						        obj = Json.createObjectBuilder()
																						        		             .add("field_name", pair.getKey())
																						        		             .add("field_value", StringUtils.join(pair.getValue(), " "))
																						        		             .build();
																						    }
																				
																					       retVal = obj.toString().replace("<em>", "").replace("</em>", "");
																					       //System.out.println("retVal: \n" + retVal);
																					   }
																					   return retVal;
																					};
	
	private final Function<Contract, String> primeAwardsConverter = c -> {
																		   JsonObject obj = Json.createObjectBuilder()
																				                .add("agency", c.getMaj_agency_cat().substring(c.getMaj_agency_cat().indexOf(":") + 2))
																				                .add("company", c.getVendorname())
																				                .add("task_order", c.getContractactiontype())
																				                .add("contract_value", c.getDollarsobligated())
																				                .build();
																		   String retVal = obj.toString();
																		   //System.out.println("retVal: \n" + retVal);
																		   return retVal;
																		};

	@Override
	public String searchContracts(String term) {
		String query = "{\n"
				+ "\"size\" : 50,"
				+ "\"query\" : {\n"
				+ "    \"filtered\" :{\n"
				+ "        \"query\" : {\n"
				+ "          \"multi_match\" : {\n"
				+ "            \"query\":      \"" + term + "\",\n"
				+ "            \"type\":       \"phrase_prefix\",\n"
				+ "            \"fields\":     [\"vendorname\", \"vendoralternatename\", \"vendorlegalorganizationname\", \"vendordoingasbusinessname\", \"maj_agency_cat\", \"maj_fund_agency_cat\", \"fundingrequestingofficeid\",  \"contractingofficeid\", \"contractingofficeagencyid\", \"mod_agency\", \"mod_parent\"],\n"
				+ "            \"lenient\": true\n"
				+ "          }\n"
				+ "        }\n"
				+ "    }\n"
				+ "},\n"
				+ "  \"highlight\": {\n"
				+ "       \"fields\" : {\n"
				+ "          \"vendorname\" : {},\n"
				+ "          \"vendoralternatename\": {},\n"
				+ "          \"vendorlegalorganizationname\": {},\n"
				+ "          \"vendordoingasbusinessname\" : {},\n"
				+ "          \"maj_agency_cat\" : {},\n"
				+ "          \"maj_fund_agency_cat\" : {},\n"
				+ "          \"contractingofficeagencyid\" : {},\n"
				+ "          \"contractingofficeid\" : {},\n"
				+ "          \"fundingrequestingofficeid\" : {},\n"
				+ "          \"mod_agency\" : {},\n"
				+ "          \"mod_parent\" : {}\n"
				+ "    }\n"
				+ "}\n"
				+ "}";
		
		
		Search search = new Search.Builder(query)
        					// multiple index or types can be added.
					        .addIndex("usaspending")
					        .addType("contract")
					        .build();
		
		SearchResult result = null;
        String retVal = "[";
		try {
			 result = jestClient.execute(search);
			 List<SearchResult.Hit<Contract, Void>> hits = result.getHits(Contract.class);
			 
			// retVal = hits.stream().map(hit -> hit.source)//.forEach(System.out::println);
			// .collect(Collectors.toList());
			 
			 List<String> highlights = hits.stream()
			     .map(autoCompleteConverter)
			     .collect(Collectors.toList());
			 
			 List<String> deduped = highlights.stream().distinct().collect(Collectors.toList());
			 if(deduped.size() > 10) {
				 deduped = deduped.subList(0, 10);
			 }
			 
			 String dedupedStr = StringUtils.join(deduped, ", ");
			 retVal = retVal + dedupedStr + "]";
			

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
					   "         \"sum\" : { \"field\" : \"dollarsobligated\" }\n" +
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
	        		                .add("total_award_value", totalAwardsVal.doubleValue())
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

	@Override
	public String getPrimeAwards(String startDt, String endDt) {
        String query = "{\n" +
	                  // " \"fields\" : [\"maj_agency_cat\", \"vendorname\", \"contractactiontype\", \"baseandalloptionsvalue\"], \n" +
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
					   " \"sort\": { \"dollarsobligated\": { \"order\": \"desc\" }} \n" +
				       "}";
        
        //System.out.println("Query: \n" + query);
       
		Search search = new Search.Builder(query)
		                                // multiple index or types can be added.
		                                .addIndex("usaspending")
		                                .addType("contract")
		                                .build();
		SearchResult result = null;
        List<String> retVal = null;
		try {
			 result = jestClient.execute(search);
			 List<SearchResult.Hit<Contract, Void>> hits = result.getHits(Contract.class);
			 //System.out.println("Got Hits: \n" + hits);
			 retVal = hits.stream()
					      .map(hit -> hit.source)
                          .map(primeAwardsConverter)
			              .collect(Collectors.toList());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder finalVal = new StringBuilder("[");
		for(String val : retVal) {
			finalVal.append(val).append(",");
		}
		
		if(finalVal.length() > 1) {
			finalVal.setLength(finalVal.length()-1);
		}
		
		finalVal.append("]");
		//System.out.println(finalVal);
		return finalVal.toString();
	}

	@Override
	public SearchResponse getSearchResults(String searchTerm, int from, int size) {
        String query = "{\n" +
                // " \"fields\" : [\"maj_agency_cat\", \"vendorname\", \"contractactiontype\", \"baseandalloptionsvalue\"], \n" +
        		 "  \"from\" : " + from + ", \"size\" : " + size + ",\n"
				+   " \"query\" : {\n"
				+ "    \"filtered\" :{\n"
				+ "        \"query\" : {\n"
				+ "          \"multi_match\" : {\n"
				+ "            \"query\":      \"" + searchTerm + "\",\n"
				+ "            \"type\":       \"phrase\",\n"
				+ "            \"fields\":     [\"vendorname\", \"vendoralternatename\", \"vendorlegalorganizationname\", \"vendordoingasbusinessname\", \"maj_agency_cat\", \"maj_fund_agency_cat\", \"fundingrequestingofficeid\",  \"contractingofficeid\", \"contractingofficeagencyid\", \"mod_agency\", \"mod_parent\"],\n"
				+ "            \"lenient\": true\n"
				+ "          }\n"
				+ "        }\n"
				+ "    }\n"
				+ "},\n" +
				   " \"sort\": {\n" +
				   "   \"signeddate\": { \"order\": \"desc\" },\n" +
				   "   \"effectivedate\": { \"order\": \"desc\" },\n" +
				   "   \"dollarsobligated\": { \"order\": \"desc\" }\n" +
				   "} \n" +
			       "}";
  
	    //System.out.println("Query: \n" + query);
	 
		Search search = new Search.Builder(query)
		                                // multiple index or types can be added.
		                                .addIndex("usaspending")
		                                .addType("contract")
		                                .build();
		SearchResult result = null;
	    List<Contract> retVal = null;
	    SearchResponse sr = null;
		try {
			 result = jestClient.execute(search);
			 List<SearchResult.Hit<Contract, Void>> hits = result.getHits(Contract.class);
			 retVal = hits.stream()
					      .map(hit -> hit.source)
			              .collect(Collectors.toList());
			 
			 sr = new SearchResponse(result.getTotal(),retVal);
			 
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sr;
	}

}
