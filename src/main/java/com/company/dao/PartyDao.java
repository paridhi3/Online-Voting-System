package com.company.dao;

import java.util.List;

import jakarta.servlet.http.Part;

public interface PartyDao {
	List<Party> getAllParties();
	int addPartyToDatabase(int party_id, String party_name, Part partySymbolPart);
	boolean deleteParty(int party_id);
}
