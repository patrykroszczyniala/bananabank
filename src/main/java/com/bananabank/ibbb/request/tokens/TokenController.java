/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.tokens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bananabank.ibbb.request.RequestUrl;
import com.bananabank.ibbb.request.tokens.core.TokenService;
import com.bananabank.ibbb.request.tokens.entity.Token;

/**
 * The main entry point for the token generation requests.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@RestController
@Scope("request")
public class TokenController {

    /** The token service. */
    private TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Generates new token.
     *
     * @param userId The user for which token should be generated.
     * @return the generated token
     */
    @RequestMapping(value = RequestUrl.GENERATE_TOKEN_URL, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Token generate(@PathVariable String userId) {
        return tokenService.generate(userId);
    }

}
