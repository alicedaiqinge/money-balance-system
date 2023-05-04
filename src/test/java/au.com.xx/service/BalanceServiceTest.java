package au.com.xx.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {
    private static BalanceService balanceService;
    private Map<Integer, Double> balanceTestMap = new HashMap<>();
    private int customerId1 = 1;
    private int customerId2 = 2;
    private double deposit1 = 2.01;
    private double deposit2 = 3.01;


    @BeforeAll
    public static void setUp() {
        balanceService = new BalanceService();
    }

    @Test
    public void test_deposit_by_customerId() {
        balanceTestMap.put(customerId1, deposit1);
        balanceService.depositByCustomerId(customerId1, deposit1);
        double balance = balanceService.getBalanceByCustomerId(customerId1);
        assertThat(balanceTestMap.get(customerId1)).isEqualTo(balance);
    }

}
