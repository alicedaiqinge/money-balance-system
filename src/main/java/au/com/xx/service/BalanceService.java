package au.com.xx.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BalanceService {

    private static Map<Integer, Double> balanceMap = new HashMap<>();

    void depositByCustomerId(int customerId, double depositAmount){
        balanceMap.put(customerId, balanceMap.getOrDefault(customerId, 0.0) + depositAmount);
    }

    void withdrawByCustomerId(int customerId, double depositAmount){
        Double remainBalance = balanceMap.getOrDefault(customerId, 0.0) - depositAmount;
        if(remainBalance < 0){
            // message to customer
            return;
        }
        balanceMap.put(customerId, remainBalance);
    }

    Double getBalanceByCustomerId(int customerId){
        return balanceMap.getOrDefault(customerId, 0.0);
    }

    Double getTotalBalance(){
        return balanceMap.values().stream().reduce(0.0, (a, b) -> a + b);
    }

}
