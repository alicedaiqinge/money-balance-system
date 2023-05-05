package au.com.xx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BalanceService {

    // TODO we maybe need to use database to store, replace map.
    private static Map<Integer, Double> balanceMap = new HashMap<>();
    private static String WITHDRAW_ERROR = "Your withdraw money should not exceed balance!";

    void depositByCustomerId(int customerId, double depositAmount){
        balanceMap.put(customerId, balanceMap.getOrDefault(customerId, 0.0) + depositAmount);
    }

    void withdrawByCustomerId(int customerId, double depositAmount) throws Exception {
        Double remainBalance = balanceMap.getOrDefault(customerId, 0.0) - depositAmount;
        if(remainBalance < 0){
            // TODO maybe we need user defined exception
            throw new Exception(WITHDRAW_ERROR);
        }
        balanceMap.put(customerId, remainBalance);
    }

    double getBalanceByCustomerId(int customerId){
        return balanceMap.getOrDefault(customerId, 0.0);
    }

    double getBankTotalBalance(){
        return balanceMap.values().stream().reduce(0.0, (a, b) -> a + b);
    }

}
