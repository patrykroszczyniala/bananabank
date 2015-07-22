/**
 * Copyright (c) 2015, Patryk Roszczyniała
 */
package com.bananabank.ibbb.core.authorization;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bananabank.ibbb.core.validaiton.UnauthorizedException;
import com.bananabank.ibbb.request.balance.entity.Authorizable;

/**
 * The authorization interceptor that checks if user passed valid token in the
 * request.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Component
public class AuthorizationInterceptor implements MethodInterceptor {

    /** The authorization service. */
    private AuthorizationService authorizationService;

    @Autowired
    public void setAuthorizationService(
            AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String userId = (String) invocation.getArguments()[0];
        Authorizable auth =
                (Authorizable) invocation.getArguments()[1];
        String requestToken = auth.getToken();

        if (!authorizationService.authorized(userId, requestToken)) {
            throw new UnauthorizedException();
        }

        return invocation.proceed();
    }
}
