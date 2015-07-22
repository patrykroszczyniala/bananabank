package com.bananabank.ibbb.unit.request.balance.core;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import com.bananabank.ibbb.core.persistence.Persistence;
import com.bananabank.ibbb.core.persistence.Session;
import com.bananabank.ibbb.request.balance.core.AccountDao;
import com.bananabank.ibbb.request.balance.core.BalanceService;
import com.bananabank.ibbb.request.balance.entity.Balance;

@RunWith(MockitoJUnitRunner.class)
public class BalanceServiceTest {

    private BalanceService balanceService;

    @Before
    public void setUp() {
        balanceService = new BalanceService(
                new AccountDao(new Session<>(new Persistence())));
    }

    @Test
    public void shouldIncreaseBalance() {
        // given
        String userId = "patryk";
        BigDecimal value = BigDecimal.TEN;
        // when
        balanceService.increase(userId, value);
        // then
        Balance current = balanceService.getBalance(userId);
        Balance expected = new Balance(BigDecimal.TEN);
        Assert.assertEquals(expected, current);
    }

    @Test
    public void shouldDecreaseBalance() {
        // given
        String userId = "patryk";
        balanceService.increase(userId, BigDecimal.TEN);
        // when
        balanceService.decrease(userId, BigDecimal.ONE);
        // then
        Balance current = balanceService.getBalance(userId);
        Balance expected = new Balance(new BigDecimal(9));
        Assert.assertEquals(expected, current);
    }

    @Test
    public void shouldReturnCurrentBalance() {
        // given
        String userId = "patryk";
        balanceService.increase(userId, BigDecimal.TEN);
        balanceService.decrease(userId, BigDecimal.ONE);
        // when
        Balance current = balanceService.getBalance(userId);
        // then
        Balance expected = new Balance(new BigDecimal(9));
        Assert.assertEquals(expected, current);
    }

}
