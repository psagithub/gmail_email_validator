package com.fetchrewards.gmail.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class EmailAddresses {
    private List<String> addresses;

    @JsonCreator
    public EmailAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public List<String> getAddresses() {
        return addresses;
    }
}
