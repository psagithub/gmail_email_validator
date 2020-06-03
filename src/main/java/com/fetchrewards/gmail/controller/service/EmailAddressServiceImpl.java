package com.fetchrewards.gmail.controller.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmailAddressServiceImpl implements EmailAddressService {
    private static final String GMAIL_SUFFIX       = "@gmail";
    private static final String GOOGLE_MAIL_SUFFIX = "@googlemail";

    @Override
    public int countUniqueEmailAddresses(List<String> addresses) {
        Set<String> uniqEmails = new HashSet<>();
        for (String address : addresses) {
            address = address.toLowerCase();
            if (isValidGmail(address)) {
                uniqEmails.add(getUnifiedAddress(address));
            }
        }
        return uniqEmails.size();
    }

    private String getUnifiedAddress(String address) {
        StringBuilder sb = new StringBuilder();
        boolean plusStarted = false;
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                continue;
            }
            else if (c == '+') {
                plusStarted = true;
                continue;
            }
            else if (c == '@') {
                break;
            }
            else if (plusStarted) {
                continue;
            }
            sb.append(c);
        }

        return sb.toString();
    }

    private boolean isValidGmail(String address) {
        return address.contains(GMAIL_SUFFIX) || address.contains(GOOGLE_MAIL_SUFFIX);
    }

    public static void main(String[] args) {
        System.out.println(new EmailAddressServiceImpl().countUniqueEmailAddresses(Arrays.asList(
                "ps.ashok1983+as.kumar.$@gmail.com",
                "ps.ashok1983@googlemail.com",
                "ps.ashok1983+name+name@gmail.com",
                "ps.ashok.1983+name@googlemail.co.in")));
    }
}
