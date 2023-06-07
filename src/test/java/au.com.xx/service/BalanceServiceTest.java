package au.com.xx.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {
    private static BalanceService balanceService;
    private int customerIdAlice = 1;
    private int customerIdAlan = 2;
    private BigDecimal deposit_30 = new BigDecimal(30.00);
    private BigDecimal deposit_40 = new BigDecimal(40.00);
    private BigDecimal withdraw_20 = new BigDecimal(20.00);
    BigDecimal balanceForAlice = BigDecimal.ZERO;
    BigDecimal balanceZero = BigDecimal.ZERO;
    private String WITHDRAW_ERROR = "Your withdraw money should not exceed balance!";


    @BeforeAll
    static void setUp() {
        balanceService = new BalanceService();
    }

    @Test
    void test_get_balance_by_customerId() throws Exception {
        clearDepositForAlice();
        BigDecimal balanceForAlice = balanceService.getBalanceByCustomerId(customerIdAlice);
        assertThat(balanceZero).isEqualTo(balanceForAlice);
    }
    @Test
    void test_deposit_by_customerId() throws Exception {
        clearDepositForAlice();
        balanceService.depositByCustomerId(customerIdAlice, deposit_30);
        BigDecimal balanceForAlice = balanceService.getBalanceByCustomerId(customerIdAlice);
        assertThat(deposit_30).isEqualTo(balanceForAlice);
    }

    @Test
    void test_withdraw_by_customerId() throws Exception {
        BigDecimal remain_10 = deposit_30.subtract(withdraw_20);
        clearDepositForAlice();
        balanceService.depositByCustomerId(customerIdAlice, deposit_30);
        balanceService.withdrawByCustomerId(customerIdAlice, withdraw_20);
        balanceForAlice = balanceService.getBalanceByCustomerId(customerIdAlice);
        assertThat(remain_10).isEqualTo(balanceForAlice);
    }

    @Test
    void test_withdraw_by_customerId_with_exception() throws Exception {
        clearDepositForAlice();

        assertThatThrownBy(() -> balanceService.withdrawByCustomerId(customerIdAlice, withdraw_20))
                .isInstanceOf(Exception.class)
                .hasMessage(WITHDRAW_ERROR);
    }

    @Test
    void test_bank_total_balance() throws Exception {
        BigDecimal remain_70 = deposit_30.add(deposit_40);
        clearDepositForAlice();

        balanceService.depositByCustomerId(customerIdAlice, deposit_30);
        balanceService.depositByCustomerId(customerIdAlan, deposit_40);
        BigDecimal bankBalance = balanceService.getBankTotalBalance();
        assertThat(remain_70).isEqualTo(bankBalance);

        balanceService.withdrawByCustomerId(customerIdAlice, deposit_30);
        bankBalance = balanceService.getBankTotalBalance();
        assertThat(deposit_40).isEqualTo(bankBalance);
    }

    private void clearDepositForAlice() throws Exception {
        balanceForAlice = balanceService.getBalanceByCustomerId(customerIdAlice);
        balanceService.withdrawByCustomerId(customerIdAlice, balanceForAlice);
        balanceForAlice = balanceService.getBalanceByCustomerId(customerIdAlice);
        assertThat(balanceZero).isEqualTo(balanceForAlice);
    }

}
