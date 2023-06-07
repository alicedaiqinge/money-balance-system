package au.com.xx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BalanceService {

    // TODO we maybe need to use database to store, replace map.
    private static Map<Integer, BigDecimal> balanceMap = new HashMap<>();
    private static String WITHDRAW_ERROR = "Your withdraw money should not exceed balance!";

    void depositByCustomerId(int customerId, BigDecimal depositAmount){
        balanceMap.put(customerId, balanceMap.getOrDefault(customerId, BigDecimal.ZERO).add(depositAmount));
    }

    void withdrawByCustomerId(int customerId, BigDecimal depositAmount) throws Exception {
        BigDecimal remainBalance = balanceMap.getOrDefault(customerId, BigDecimal.ZERO).subtract(depositAmount);
        if(remainBalance.compareTo(BigDecimal.ZERO) < 0){
            // TODO maybe we need user defined exception
            throw new Exception(WITHDRAW_ERROR);
        }
        balanceMap.put(customerId, remainBalance);
    }

    BigDecimal getBalanceByCustomerId(int customerId){
        return balanceMap.getOrDefault(customerId, BigDecimal.ZERO);
    }

    BigDecimal getBankTotalBalance(){
        return balanceMap.values().stream().reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
    }

}
