package au.com.xx.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {
    private static BalanceService balanceService;
    private int customerIdAlice = 1;
    private int customerIdAlan = 2;
    private double deposit_30 = 30.00;
    private double deposit_40 = 40.00;
    private double withdraw_20 = 20.00;
    double balanceForAlice = 0.00;
    double balanceZero = 0.00;
    private String WITHDRAW_ERROR = "Your withdraw money should not exceed balance!";


    @BeforeAll
    static void setUp() {
        balanceService = new BalanceService();
    }

    @Test
    void test_get_balance_by_customerId() throws Exception {
        clearDepositForAlice();
        double balanceForAlice = balanceService.getBalanceByCustomerId(customerIdAlice);
        assertThat(balanceZero).isEqualTo(balanceForAlice);
    }
    @Test
    void test_deposit_by_customerId() throws Exception {
        clearDepositForAlice();
        balanceService.depositByCustomerId(customerIdAlice, deposit_30);
        double balanceForAlice = balanceService.getBalanceByCustomerId(customerIdAlice);
        assertThat(deposit_30).isEqualTo(balanceForAlice);
    }

    @Test
    void test_withdraw_by_customerId() throws Exception {
        double remain_10 = deposit_30 - withdraw_20;
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
        double remain_70 = deposit_30 + deposit_40;
        clearDepositForAlice();

        balanceService.depositByCustomerId(customerIdAlice, deposit_30);
        balanceService.depositByCustomerId(customerIdAlan, deposit_40);
        double bankBalance = balanceService.getBankTotalBalance();
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
