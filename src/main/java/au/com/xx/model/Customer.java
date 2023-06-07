package au.com.xx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    // TODO if we use dabatase, will use this model
    int id;
    String name;
    BigDecimal balance;

    String accountNumber;
}
