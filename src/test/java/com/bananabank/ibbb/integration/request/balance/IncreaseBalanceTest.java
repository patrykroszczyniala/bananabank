package com.bananabank.ibbb.integration.request.balance;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.bananabank.ibbb.integration.IntegrationTest;
import com.bananabank.ibbb.request.RequestUrl;

public class IncreaseBalanceTest extends IntegrationTest {

    @Test
    public void shouldReturn401ResponseCodeForIncorrectPassword()
            throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.INCREASE_BALANCE_URL, userId);
        // then
        mvc.perform(
                MockMvcRequestBuilders
                        .post(url)
                        .content("{\"value\":100, \"token\":\"wrongPassword\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void shouldReturn200StatusCodeOnSuccess()
            throws Exception {
        // given
        final String userId = "brzeczybrzuch";
        final BigDecimal requestedValue = new BigDecimal(100);
        // when
        MvcResult response = request(mvc).increaseBalance(userId,
                requestedValue);
        // then
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual =
                HttpStatus.valueOf(response.getResponse().getStatus());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldContain$200InTheBalance()
            throws Exception {
        // given
        final String userId = "joanna";
        final BigDecimal value = new BigDecimal(100);
        // when
        request(mvc).increaseBalance(userId, value);
        request(mvc).increaseBalance(userId, value);
        // then
        BigDecimal current = request(mvc).checkBalance(userId).getValue();
        BigDecimal expected = new BigDecimal(200);
        Assert.assertEquals(expected, current);
    }

    @Test
    public void shouldReturn400StatusForNegativeRequestedValue()
            throws Exception {
        // given
        final String userId = "patryk";
        final BigDecimal requestedValue = new BigDecimal(-100);
        // when
        MvcResult response =
                request(mvc).increaseBalance(userId, requestedValue);
        // then
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        HttpStatus actual =
                HttpStatus.valueOf(response.getResponse().getStatus());
        Assert.assertEquals(expected, actual);
    }

}
