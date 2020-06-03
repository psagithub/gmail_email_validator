package com.fetchrewards.gmail.controller.service;

import java.util.List;

public interface EmailAddressService {
    int countUniqueEmailAddresses(List<String> addresses);
}
