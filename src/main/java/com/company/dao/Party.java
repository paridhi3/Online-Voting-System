package com.company.dao;

import java.util.Arrays;

public class Party {
    private int party_id;
    private String party_name;
    private byte[] party_symbol;
    private int vote_count;

    public Party(int party_id, String party_name, byte[] party_symbol, int vote_count) {
        this.party_id = party_id;
        this.party_name = party_name;
        this.party_symbol = party_symbol;
        this.vote_count = vote_count;
    }

    public int getParty_id() {
        return party_id;
    }

    public String getParty_name() {
        return party_name;
    }

    public byte[] getParty_symbol() {
        return party_symbol;
    }

    public int getVote_count() {
        return vote_count;
    }

    @Override
    public String toString() {
        // Use Arrays.toString() to display the byte[] as a string
        return "Party{" +
               "party_id=" + party_id +
               ", party_name='" + party_name + '\'' +
               ", party_symbol=" + Arrays.toString(party_symbol) +
               ", vote_count=" + vote_count +
               '}';
    }
}
