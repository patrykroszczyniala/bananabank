package com.bananabank.ibbb.integration.request.tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.bananabank.ibbb.integration.IntegrationTest;
import com.bananabank.ibbb.request.RequestUrl;
import com.bananabank.ibbb.request.tokens.entity.Token;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenControllerTest extends IntegrationTest {

    @Test
    public void shouldGenerateJsonResponse() throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.GENERATE_TOKEN_URL, userId);
        // when
        MvcResult response =
                mvc.perform(MockMvcRequestBuilders.post(url)).andReturn();
        // then
        Assert.assertEquals(
                "application/json;charset=UTF-8",
                response.getResponse().getContentType());

    }

    @Test
    public void shouldGeneratorTokenObject() throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.GENERATE_TOKEN_URL, userId);
        // when
        MvcResult response =
                mvc.perform(MockMvcRequestBuilders.post(url)).andReturn();
        // then
        String jsonResult = response.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.readValue(jsonResult, Token.class);
    }

    @Test
    public void shouldGenerateJsonTokenResponse() throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.GENERATE_TOKEN_URL, userId);
        // when
        MvcResult response =
                mvc.perform(MockMvcRequestBuilders.post(url)).andReturn();
        // then
        String jsonResult = response.getResponse().getContentAsString();
        Pattern p = Pattern.compile("\\{\"token\":\"(.*)\"\\}");
        Matcher matcher = p.matcher(jsonResult);
        Assert.assertTrue(matcher.matches());
    }

    @Test
    public void shouldReturn201StatusCodeOnSuccess() throws Exception {
        // given
        final String userId = "patryk";
        final String url =
                RequestUrl.create(RequestUrl.GENERATE_TOKEN_URL, userId);
        // then
        mvc.perform(MockMvcRequestBuilders.post(url))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
