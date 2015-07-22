package com.bananabank.ibbb.integration.request.history;

import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.bananabank.ibbb.integration.IntegrationTest;
import com.bananabank.ibbb.request.RequestUrl;
import com.bananabank.ibbb.request.history.entity.Transaction;
import com.bananabank.ibbb.request.history.entity.Transaction.Type;
import com.bananabank.ibbb.request.history.entity.Transactions;
import com.google.common.collect.Lists;

public class HistoryControllerTest extends IntegrationTest {

    @Test
    public void shouldReturnEmptyResultForNoTransactions()
            throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.CHECK_HISTORY_URL, userId);
        // when
        MvcResult response =
                mvc.perform(MockMvcRequestBuilders.get(url)).andReturn();
        // then
        final String expectedJson = "{\"transactions\":[]}";
        final String returnedJson = response.getResponse().getContentAsString();
        Assert.assertEquals(expectedJson, returnedJson);
    }

    @Test
    public void shouldReturn200StatusCodeOnSuccess()
            throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.CHECK_HISTORY_URL, userId);
        // then
        mvc.perform(
                MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnTransactionsOrderedByDate()
            throws Exception {
        // given
        final String userId = "patryk";
        request(mvc).increaseBalance(userId, BigDecimal.TEN);
        request(mvc).decreaseBalance(userId, BigDecimal.ONE);
        request(mvc).decreaseBalance(userId, BigDecimal.ONE);
        // when
        Transactions returnedTransactions = request(mvc).checkHistory(userId);
        // then
        List<Transaction> transactions =
                Lists.newArrayList(
                        new Transaction(BigDecimal.TEN, Type.INCREASE),
                        new Transaction(BigDecimal.ONE, Type.DECREASE),
                        new Transaction(BigDecimal.ONE, Type.DECREASE));
        Transactions expectedTransactions = new Transactions(transactions);
        Assert.assertEquals(
                expectedTransactions.toString(),
                returnedTransactions.toString());
    }
}
