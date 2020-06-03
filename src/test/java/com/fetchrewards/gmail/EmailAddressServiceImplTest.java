package com.fetchrewards.gmail;

import com.fetchrewards.gmail.controller.service.EmailAddressService;
import com.fetchrewards.gmail.controller.service.EmailAddressServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class EmailAddressServiceImplTest {
    private EmailAddressService service;

    @BeforeEach
    public void init() {
        service = new EmailAddressServiceImpl();
    }

    @Test
    public void countUniqueEmailAddresses() {
        assertThat(service.countUniqueEmailAddresses(Arrays.asList(
                "test.email2020+as.test.$@gmail.com",
                "test.email2020@googlemail.com",
                "test.email2020+name+name@gmail.com",
                "test.email.2020+name@googlemail.co.in"))).isEqualTo(1);

        assertThat(service.countUniqueEmailAddresses(Arrays.asList(
                "test.email2021+as.test.$@gmail.com",
                "test_email2021+as.test.$@gmail.com",
                "test.email2020@googlemail.com"))).isEqualTo(3);
    }
}
