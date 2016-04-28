package gov.fpds.domain;

import java.util.List;

public class SearchResponse {
	private Integer total;
	private List<Contract> transactions;
	
	public SearchResponse(int total, List<Contract> transactions) {
		this.total = total;
		this.transactions = transactions;
	}
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<Contract> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Contract> transactions) {
		this.transactions = transactions;
	}
}
