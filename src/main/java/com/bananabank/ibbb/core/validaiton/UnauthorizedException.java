/**
 * Copyright (c) 2015, Patryk Roszczyniała
 */
package com.bananabank.ibbb.core.validaiton;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bananabank.ibbb.core.authorization.AuthorizationInterceptor;

/**
 * Thrown if user is not authorized to specified operation, eg. increase,
 * decrease balance. Used in
 * {@link AuthorizationInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)}
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

}
