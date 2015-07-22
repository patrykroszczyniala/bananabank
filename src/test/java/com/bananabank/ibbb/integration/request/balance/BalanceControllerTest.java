/**
 * Copyright (c) 2015, Patryk Roszczyniała for Banana Best Bank Inc.
 * All rights reserved.
 */
package com.bananabank.ibbb.integration.request.balance;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.bananabank.ibbb.request.balance.BalanceController;

/**
 * The suite case created to have all 'balance' related tests in one place. It's
 * also easier to find all 'balance' related tests (eg. with MoreUnit) because
 * this test class has the same name as {@link BalanceController}.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 */
@RunWith(Suite.class)
@SuiteClasses({
        CurrentBalanceTest.class,
        IncreaseBalanceTest.class,
        DecreaseBalanceTest.class})
public class BalanceControllerTest {

}
