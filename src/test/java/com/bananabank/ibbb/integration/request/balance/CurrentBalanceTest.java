package com.bananabank.ibbb.integration.request.balance;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.bananabank.ibbb.integration.IntegrationTest;
import com.bananabank.ibbb.request.RequestUrl;
import com.bananabank.ibbb.request.balance.entity.Balance;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrentBalanceTest extends IntegrationTest {

    @Test
    public void shouldReturnEmptyResult() throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.CHECK_BALANCE_URL, userId);
        // when
        MvcResult response =
                mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        String jsonResponse = response.getResponse().getContentAsString();
        // then
        Assert.assertEquals("{\"value\":0}", jsonResponse);
    }

    @Test
    public void shouldReturnBalanceObject() throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.CHECK_BALANCE_URL, userId);
        // when
        MvcResult response =
                mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        String jsonResponse = response.getResponse().getContentAsString();
        // then
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(jsonResponse, Balance.class);
    }

    @Test
    public void shouldReturn100InTheResult() throws Exception {
        // given
        final String userId = "patryk";
        final BigDecimal expectedBalance = new BigDecimal(100);
        request(mvc).increaseBalance(userId, expectedBalance);
        // when
        Balance returnedBalance = request(mvc).checkBalance(userId);
        // then
        Assert.assertEquals(
                expectedBalance,
                returnedBalance.getValue());
    }
}
