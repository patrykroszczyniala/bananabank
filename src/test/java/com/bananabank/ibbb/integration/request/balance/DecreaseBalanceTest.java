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

public class DecreaseBalanceTest extends IntegrationTest {

    @Test
    public void shouldReturn401ResponseCodeForIncorrectPassword()
            throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.DECREASE_BALANCE_URL, userId);
        // when
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
        final String userId = "patryk";
        final BigDecimal value = BigDecimal.ZERO;
        // when
        MvcResult response = request(mvc).decreaseBalance(userId, value);
        // then
        HttpStatus expected = HttpStatus.OK;
        HttpStatus actual = HttpStatus.valueOf(response.getResponse()
                .getStatus());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldContain$200InTheBalance()
            throws Exception {
        // given
        final String userId = "joanna";
        final BigDecimal increaseValue = new BigDecimal(300);
        request(mvc).increaseBalance(userId, increaseValue);
        // when
        final BigDecimal decreaseValue = new BigDecimal(100);
        request(mvc).decreaseBalance(userId, decreaseValue);
        // then
        BigDecimal actual = request(mvc).checkBalance(userId).getValue();
        BigDecimal expected = new BigDecimal(200);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturn400StatusForNegativeRequestedValue()
            throws Exception {
        // given
        final String userId = "john";
        BigDecimal requestedValue = new BigDecimal(-100);
        // when
        MvcResult response = request(mvc).decreaseBalance(userId,
                requestedValue);
        // then
        Assert.assertEquals(
                HttpStatus.BAD_REQUEST,
                HttpStatus.valueOf(response.getResponse().getStatus()));
    }

    @Test
    public void shouldReturn400StatusIfBalanceWillBeNegative()
            throws Exception {
        // given
        final String userId = "patryk";
        request(mvc).increaseBalance(userId, BigDecimal.ONE);
        // when
        MvcResult response = request(mvc).decreaseBalance(userId,
                BigDecimal.TEN);
        Assert.assertEquals(
                HttpStatus.BAD_REQUEST,
                HttpStatus.valueOf(response.getResponse().getStatus()));
    }

}
