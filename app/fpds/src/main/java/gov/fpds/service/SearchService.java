package gov.fpds.service;

import gov.fpds.domain.Contract;

import java.util.List;

public interface SearchService {
	public List<Contract> searchContracts(String term);
}
