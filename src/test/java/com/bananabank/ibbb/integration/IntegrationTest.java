/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.integration;

import static com.bananabank.ibbb.request.RequestUrl.CHECK_BALANCE_URL;
import static com.bananabank.ibbb.request.RequestUrl.CHECK_HISTORY_URL;
import static com.bananabank.ibbb.request.RequestUrl.DECREASE_BALANCE_URL;
import static com.bananabank.ibbb.request.RequestUrl.GENERATE_TOKEN_URL;
import static com.bananabank.ibbb.request.RequestUrl.INCREASE_BALANCE_URL;
import static com.bananabank.ibbb.request.RequestUrl.create;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.request.RequestUrl;
import com.bananabank.ibbb.request.balance.BalanceController;
import com.bananabank.ibbb.request.balance.entity.Balance;
import com.bananabank.ibbb.request.history.HistoryController;
import com.bananabank.ibbb.request.history.entity.Transactions;
import com.bananabank.ibbb.request.tokens.TokenController;
import com.bananabank.ibbb.request.tokens.entity.Token;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Basic class for integration tests.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/test-context.xml"})
public abstract class IntegrationTest {

    @Autowired
    private Persistence persistence;

    @Autowired
    private BalanceController balanceController;

    @Autowired
    private HistoryController historyController;

    @Autowired
    private TokenController tokenController;

    protected MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        persistence.clear();
        mvc = MockMvcBuilders.standaloneSetup(balanceController,
                historyController, tokenController).build();
    }

    protected Request request(MockMvc mvc) {
        return new Request(mvc);
    }

    /**
     * Utility class that helps to send rest requests.
     *
     * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
     */
    protected static class Request {

        private MockMvc mvc;

        private Request(final MockMvc mvc) {
            this.mvc = mvc;
        }

        /**
         * Requests for new token (generates token by requesting url
         * {@link RequestUrl#GENERATE_TOKEN_URL}).
         *
         * @param userId The user id for which token should be generated
         * @return the generated and persisted token
         * @throws Exception the exception
         */
        public Token generateToken(final String userId) throws Exception {
            final String url = create(GENERATE_TOKEN_URL, userId);
            MvcResult response = mvc.perform(MockMvcRequestBuilders.post(url))
                    .andReturn();

            String jsonResult = response.getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonResult, Token.class);
        }

        /**
         * Increases balance by requesting url
         * {@link RequestUrl#INCREASE_BALANCE_URL}.
         *
         * @param userId The user id for which balance should be increased
         * @param value The value by which balance should be increased
         * @return The mvc result (request result)
         * @throws Exception the exception
         */
        public MvcResult increaseBalance(final String userId,
                final BigDecimal value)
                throws Exception {
            final Token token = generateToken(userId);
            final String url = create(INCREASE_BALANCE_URL, userId);
            final String json =
                    String.format("{\"value\":%s, \"token\":\"%s\"}",
                            value, token.getToken());
            return mvc.perform(
                    MockMvcRequestBuilders
                            .post(url)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        }

        /**
         * Decreases balance by requesting url
         * {@link RequestUrl#DECREASE_BALANCE_URL}.
         *
         * @param userId The user id for which balance should be decreased
         * @param value The value by which balance should be decreased
         * @return The mvc result (request result)
         * @throws Exception the exception
         */
        public MvcResult decreaseBalance(final String userId,
                final BigDecimal value)
                throws Exception {
            final Token token = generateToken(userId);
            final String url = create(DECREASE_BALANCE_URL, userId);
            final String json =
                    String.format("{\"value\":%s, \"token\":\"%s\"}",
                            value, token.getToken());
            return mvc.perform(
                    MockMvcRequestBuilders
                            .post(url)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
        }

        /**
         * Check balance by requesting url {@link RequestUrl#CHECK_BALANCE_URL}.
         *
         * @param userId The user for which balance should be checked
         * @return the current balance
         * @throws Exception the exception
         */
        public Balance checkBalance(final String userId)
                throws Exception {
            final String url = create(CHECK_BALANCE_URL, userId);
            MvcResult response = mvc.perform(
                    MockMvcRequestBuilders.get(url))
                    .andReturn();

            String jsonResult = response.getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsonResult, Balance.class);
        }

        /**
         * Check history if user transactions requesting url
         * {@link RequestUrl#CHECK_HISTORY_URL}.
         *
         * @param userId The user for which history transactions hsould be
         *        checked
         * @return The transactions history
         * @throws Exception the exception
         */
        public Transactions checkHistory(final String userId) throws Exception {
            final String url = create(CHECK_HISTORY_URL, userId);
            MvcResult response = mvc.perform(
                    MockMvcRequestBuilders.get(url))
                    .andReturn();

            String jsonResult = response.getResponse().getContentAsString();
            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(jsonResult, Transactions.class);
        }
    }

}
