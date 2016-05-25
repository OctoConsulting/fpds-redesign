package gov.fpds.service;

import gov.fpds.domain.Contract;
import gov.fpds.domain.SearchResponse;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.Suggest;
import io.searchbox.core.SuggestResult;
import io.searchbox.core.search.aggregation.Aggregation;
import io.searchbox.core.search.aggregation.CardinalityAggregation;
import io.searchbox.core.search.aggregation.DateHistogramAggregation;
import io.searchbox.core.search.aggregation.DateHistogramAggregation.DateHistogram;
import io.searchbox.core.search.aggregation.SumAggregation;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	JestClient jestClient;
	
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter quarterFormatter = DateTimeFormatter.ofPattern("yyyy-QQQ");
	
	
	private final Function<Hit<Contract, Void>, String> autoCompleteConverter = h -> {
																					   Map<String, List<String>> highlights = h.highlight;
																					   String retVal = "";
																					   if(highlights != null && highlights.size() > 0) {
																						    Iterator<Entry<String, List<String>>> it = highlights.entrySet().iterator();
																						    JsonObject obj = null;
																						    if (it.hasNext()) {
																						        Map.Entry<String, List<String>> pair = it.next();
																						        String value = StringUtils.join(pair.getValue(), " ");
																						        if(value.indexOf(":") != -1) {
																						        	value = value.substring(value.indexOf(":") + 2);
																						        }
																						        obj = Json.createObjectBuilder()
																						        		             .add("field_name", pair.getKey())
																						        		             .add("field_value", value)
																						        		             .build();
																						    }
																				
																					       retVal = obj.toString().replace("<em>", "").replace("</em>", "");
																					       //System.out.println("retVal: \n" + retVal);
																					   }
																					   return retVal;
																					};
																					
	private static Predicate<Hit<Contract, Void>> startsWith(String term) {
	    return h -> {
			   Map<String, List<String>> highlights = h.highlight;
			   String retVal = "";
			   if(highlights != null && highlights.size() > 0) {
				    Iterator<Entry<String, List<String>>> it = highlights.entrySet().iterator();
				    if (it.hasNext()) {
				        Map.Entry<String, List<String>> pair = it.next();
				        retVal = StringUtils.join(pair.getValue(), " ");
				        if(retVal.indexOf(":") != -1) {
				        	retVal = retVal.substring(retVal.indexOf(":") + 2);
				        }
				    }
			   }
			  //System.out.println("field_value is :" + retVal + " Term is: " + term); 
	    	  return retVal.startsWith("<em>");
	    };  
	}

	
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
				 .filter(startsWith(term))
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
				+ "            \"fields\":     [\"vendorname\", \"vendoralternatename\", \"vendorlegalorganizationname\", \"vendordoingasbusinessname\", \"maj_agency_cat\", \"maj_fund_agency_cat\", \"fundingrequestingofficeid\",  \"contractingofficeid\", \"contractingofficeagencyid\", \"mod_agency\", \"mod_parent\", \"principalnaicscode\", \"dunsnumber\"],\n"
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

	@Override
	public List<Contract> getTransactionsByPiid(String piid) {
        String query = "{\n"
        		+ "   \"size\" : 100,\n"
        		+ "    \"query\" : {\n"
        		+ "        \"filtered\": {\n"
        		+ "            \"query\": {\n"
        		+ "                \"match\": {\n"
        		+ "                    \"piid\": \"" + piid + "\"\n"
        		+ "                }\n"
        		+ "            }\n"
        		+ "        }\n"
        		+ "    },\n"
        		+ "   \"sort\" : {\n"
        		+ "     \"signeddate\": { \"order\": \"desc\" },\n"
        		+ "     \"modnumber\": { \"order\": \"desc\" }\n"
        		+ "   },\n"
        		+ "    \"_source\": {\n"
        		+ "        \"include\": [\n"
        		+ "            \"piid\", \"modnumber\", \"reasonformodification\", \"maj_fund_agency_cat\", \"vendorname\", \n"
        		+ "            \"signeddate\", \"effectivedate\",\"currentcompletiondate\",\n"
        		+ "            \"contractactiontype\", \"principalnaicscode\", \"dollarsobligated\", \n"
        		+ "            \"baseandalloptionsvalue\", \"dunsnumber\"\n"
        		+ "        ]\n"
        		+ "    }\n}";
  
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
		
		return retVal;
	}

	@Override
	public Map<String, List<Contract>> getTransactionsByIdvid(String idvid) {
        String query = "{\n"
        		+ "   \"size\" : 100,\n"
        		+ "    \"query\" : {\n"
        		+ "        \"filtered\": {\n"
        		+ "            \"query\": {\n"
        		+ "                \"match\": {\n"
        		+ "                    \"idvpiid\": \"" + idvid + "\"\n"
        		+ "                }\n"
        		+ "            }\n"
        		+ "        }\n"
        		+ "    },\n"
        		+ "   \"sort\" : {\n"
        		+ "     \"signeddate\": { \"order\": \"desc\" },\n"
        		+ "     \"piid\": { \"order\": \"desc\" },\n"
        		+ "     \"modnumber\": { \"order\": \"desc\" }\n"
        		+ "   },\n"
        		+ "    \"_source\": {\n"
        		+ "        \"include\": [\n"
        		+ "            \"idvpiid\", \"piid\", \"modnumber\", \"reasonformodification\",\"maj_fund_agency_cat\", \"vendorname\", \n"
        		+ "            \"signeddate\", \"effectivedate\",\"currentcompletiondate\",\n"
        		+ "            \"contractactiontype\", \"principalnaicscode\", \"dollarsobligated\", \n"
        		+ "            \"baseandalloptionsvalue\", \"dunsnumber\"\n"
        		+ "        ]\n"
        		+ "    }\n}";
  
	    //System.out.println("Query: \n" + query);
	 
		Search search = new Search.Builder(query)
		                                // multiple index or types can be added.
		                                .addIndex("usaspending")
		                                .addType("contract")
		                                .build();
		SearchResult result = null;
		Map<String, List<Contract>> retVal = null;

		try {
			 result = jestClient.execute(search);
			 List<SearchResult.Hit<Contract, Void>> hits = result.getHits(Contract.class);
			 retVal = hits.stream()
					      .map(hit -> hit.source)
					      .collect(Collectors.groupingBy(
					    		  Contract::getPiid, LinkedHashMap::new, Collectors.toList()));
			 
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return retVal;
	}

	@Override
	public String getResultsAnalytics(String searchTerm, String startDt, String endDt) {
        String query = "{\n" 
				+   " \"query\" : {\n"
				+ "    \"filtered\" :{\n"
				+ "        \"query\" : {\n"
				+ "          \"multi_match\" : {\n"
				+ "            \"query\":      \"" + searchTerm + "\",\n"
				+ "            \"type\":       \"phrase\",\n"
				+ "            \"fields\":     [\"vendorname\", \"vendoralternatename\", \"vendorlegalorganizationname\", \"vendordoingasbusinessname\", \"maj_agency_cat\", \"maj_fund_agency_cat\", \"fundingrequestingofficeid\",  \"contractingofficeid\", \"contractingofficeagencyid\", \"mod_agency\", \"mod_parent\"]\n"
				+ "          }\n"
				+ "        },\n"
				+ "         \"filter\": {\n"
				+ "             \"range\": {\n"
				+ "                 \"signeddate\": {\n"
				+ "                         \"gte\": \""+ startDt + "\",\n"
				+ "                         \"lte\": \""+ endDt + "\"\n"
				+ "                     }\n"
				+ "             }\n"
				+ "         }\n"				
				+ "    }\n"
				+ "},\n"
			    + "\"aggs\" : {\n"
			    + "        \"num_contracts\" : {\n"
			    + "            \"cardinality\" : {\n"
			    + "                \"field\" : \"piid\"\n"
			    + "            }\n"
			    + "        },\n"
			    + "        \"num_idvs\" : {\n"
			    + "            \"cardinality\" : {\n"
			    + "                \"field\" : \"idvpiid\"\n"
			    + "            }\n"
			    + "        },\n"
			    + "        \"sum_obligated\": {\n"
			    + "            \"sum\" : { \"field\" : \"dollarsobligated\" }\n"
			    + "        },\n"
			    + "        \"num_vendors\" : {\n"
			    + "            \"cardinality\" : {\n"
			    + "                \"field\" : \"dunsnumber\"\n"
			    + "            }\n"
			    + "        },\n"
			    + "        \"num_states\" : {\n"
			    + "            \"cardinality\" : {\n"
			    + "                \"field\" : \"state\"\n"
			    + "            }\n"
			    + "        },\n"
			    + "        \"num_agencies\" : {\n"
			    + "            \"cardinality\" : {\n"
			    + "                \"field\" : \"agencyid\"\n"
			    + "            }\n"
			    + "        },\n"
			    + "        \"amount_by_quarter\" : {\n"
			    + "            \"date_histogram\" : {\n"
			    + "                \"field\" : \"signeddate\",\n"
			    + "                \"interval\" : \"quarter\",\n"
			    + "                \"format\" : \"yyyy-MM-dd\",\n"
                + "                \"order\" : { \"_key\" : \"desc\" }\n"
			    + "            },\n"
			    + "            \"aggs\" : {\n"
			    + "                \"quarter_amount\" : {\n"
			    + "                    \"sum\" : {\n"
			    + "                       \"field\" : \"dollarsobligated\"    \n"
			    + "                    }\n"
			    + "                }\n "
			    + "           }\n"
			    + "        }\n"
			    + "    }\n"			    
		        + "}";
  
	    //System.out.println("Query: \n" + query);
	 
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
			Map<String, Class> nameToTypeMap = new LinkedHashMap<>(); 
			
	        nameToTypeMap.put("num_contracts", CardinalityAggregation.class);
	        nameToTypeMap.put("num_idvs", CardinalityAggregation.class);
	        nameToTypeMap.put("sum_obligated", SumAggregation.class);
	        nameToTypeMap.put("num_vendors", CardinalityAggregation.class);
	        nameToTypeMap.put("num_states", CardinalityAggregation.class);
	        nameToTypeMap.put("num_agencies", CardinalityAggregation.class);
	        nameToTypeMap.put("amount_by_quarter", DateHistogramAggregation.class);
	        
	        List<Aggregation> aggregations = result.getAggregations().getAggregations(nameToTypeMap); 
	        
	        CardinalityAggregation numContractsAgg = (CardinalityAggregation)  aggregations.get(0);
	        Long numContractsVal = numContractsAgg.getCardinality();
	        
	        CardinalityAggregation numIdvs = (CardinalityAggregation)  aggregations.get(1);
	        Long numIdvsVal = numIdvs.getCardinality();	        

	        SumAggregation sumObligated = (SumAggregation) aggregations.get(2);
	        Double sumObligatedVal = sumObligated.getSum();
	        
	        CardinalityAggregation numVendors = (CardinalityAggregation)  aggregations.get(3);
	        Long numVendorsVal = numVendors.getCardinality();
	        
	        CardinalityAggregation numStates = (CardinalityAggregation)  aggregations.get(4);
	        Long numStatesVal = numStates.getCardinality();
	        
	        CardinalityAggregation numAgencies = (CardinalityAggregation)  aggregations.get(5);
	        Long numAgenciesVal = numAgencies.getCardinality();	      
	        
	        DateHistogramAggregation amtByQuarter = (DateHistogramAggregation) aggregations.get(6);
	        List<DateHistogram> dateHistList = amtByQuarter.getBuckets();     
	        
	        Map<String, Double> values = dateHistList
	        		                     .stream()
	        		                     .collect(Collectors.toMap(DateHistogram :: getTimeAsString, 
	        		                    		                   extractObligAmt,
	        		                    		                   (u, v) -> {
	        		                    		                	   throw new IllegalStateException(String.format("Duplicate key %s", u));
	        		                    		                	}, 
																  LinkedHashMap::new));

	        JsonArrayBuilder arrKeyBuilder = Json.createArrayBuilder();
	        JsonArrayBuilder arrValueBuilder = Json.createArrayBuilder();	        

	        values.forEach((k, v) -> {
	        	LocalDate parsedDate = LocalDate.parse(k, formatter);
	        	String text = parsedDate.format(quarterFormatter);
	        	//System.out.println("Formatted date: " + text);
	        	arrKeyBuilder.add(text);
	        	arrValueBuilder.add(v);
	        });
	        
	        JsonObject qtrAmtObj = Json.createObjectBuilder()
	        		              .add("quarters", arrKeyBuilder.build())
	        		              .add("amts", arrValueBuilder.build())
	        		              .build();

	        
	        JsonObject resultObj = Json.createObjectBuilder()
	        		                .add("num_contracts", numContractsVal.intValue())
	        		                .add("num_idvs", numIdvsVal.intValue())
	        		                .add("sum_obligated", sumObligatedVal.doubleValue())
	        		                .add("num_vendors", numVendorsVal.intValue())
	        		                .add("num_states", numStatesVal.intValue())
	        		                .add("num_agencies", numAgenciesVal.intValue())
	        		                .add("amt_by_quarter", qtrAmtObj)
	        		                .build();
	        if(resultObj != null) {
	        	results = resultObj.toString();
	        	//System.out.println("Final Output: " + results);
	        }
	        

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return results;

	}
	
	private final Function<DateHistogram, Double> extractObligAmt = d -> {
		   SumAggregation sa = d.getSumAggregation("quarter_amount");
		   Double amt = sa.getSum();

		   //System.out.println("amt: \n" + amt);
		   return amt;
	};

	@Override
	public List<String> autoSuggest(String term) {
		String query = "{\n"
				+ "    \"agency_suggest\":{\n"
				+ "        \"text\":\"" + term + "\",\n"
				+ "        \"completion\": {\n"
				+ "            \"field\" : \"name_suggest\"\n"
				+ "        }\n"
				+ "    }\n"
				+ "}";
		
		Suggest suggest = new Suggest.Builder(query)
		                      .addIndex("search_suggest")
		                      //.addIndex("test_index")
				              .build();
		
		//System.out.println("Suggest URI: " + suggest.getURI());
		//System.out.println("query: " + query);
		
		SuggestResult result = null;
		try {
			result = jestClient.execute(suggest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        //assertTrue(result.getErrorMessage(), result.isSucceeded()); 
 
        List<SuggestResult.Suggestion> suggestions = result.getSuggestions("agency_suggest"); 
        //assertEquals(3, suggestions.size());
 
        SuggestResult.Suggestion suggestion = suggestions.get(0); 
        
        List<Map<String, Object>> results = suggestion.options;		
        List<String> textList = results.stream()
        		                .map(vMap -> vMap.get("text").toString().toUpperCase())
        		                .distinct()
        		                .collect(Collectors.toList());
        
		return textList;

	}

}
