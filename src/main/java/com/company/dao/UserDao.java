package com.company.dao;

public interface UserDao {

	boolean isValidUser(int voter_id, String password);
	boolean addUser(User user);
	boolean isVoterIdUnique(int voter_id);
	String getNameByVoterId(int voter_id);
	boolean isValidAdmin(String admin_username, String security_key);
	boolean hasVoted(int voter_id);
	void castVote(int voter_id, int party_id);
}
