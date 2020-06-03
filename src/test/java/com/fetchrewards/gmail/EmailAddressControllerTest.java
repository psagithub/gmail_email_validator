package com.fetchrewards.gmail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetchrewards.gmail.entity.EmailAddresses;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@SpringBootTest
@AutoConfigureMockMvc
public class EmailAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper serializer;

    @Test
    public void getUniqAddressCountWithGET() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(
                "/email/uniqcount?addresses=test.email2020+as.test.$@gmail.com,test.email2020@googlemail.com,test.email2020+name+name@gmail.com,test.email.2020+name@googlemail.co.in"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.count", CoreMatchers.is(1)));
    }

    @Test
    public void getUniqAddressCountWithPOST() throws Exception {
        String reqBody = serializer.writeValueAsString(
                new EmailAddresses(Arrays.asList("test.email2020+as.test.$@gmail.com",
                                                 "test.email2020@googlemail.com",
                                                 "test.email2020+name+name@gmail.com",
                                                 "test.email.2020+name@googlemail.co.in")));
        mockMvc.perform(MockMvcRequestBuilders.post(
                "/email/uniqcount").content(reqBody)
                                              .contentType(MediaType.APPLICATION_JSON_VALUE))
               .andExpect(MockMvcResultMatchers.jsonPath("$.count", CoreMatchers.is(1)));
    }
}
