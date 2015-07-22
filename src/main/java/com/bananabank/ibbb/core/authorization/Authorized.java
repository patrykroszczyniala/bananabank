/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.core.authorization;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.bananabank.ibbb.request.balance.BalanceController;
import com.bananabank.ibbb.request.balance.entity.BalanceRequest;

/**
 * The Annotation that authorizes request. First method parameter should be
 * userId (string) and second parameter should be {@link BalanceRequest}, eg.
 * {@link BalanceController#increase(String, BalanceRequest)}.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface Authorized {

}
