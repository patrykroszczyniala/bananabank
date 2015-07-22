/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.request.history.core;

import java.lang.reflect.Method;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * History advisor used by history interceptor ({@link HistoryInterceptor})
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 * @version $Id$
 */
@Component
public class HistoryAdvisor extends AbstractPointcutAdvisor {

    private static final long serialVersionUID = 1L;

    /** The pointcut (annotation matcher). */
    private StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return method.isAnnotationPresent(Audited.class);
        }
    };

    /** The interceptor executed on {@link Audited} annotation. */
    @Autowired
    private HistoryInterceptor interceptor;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return interceptor;
    }

}
