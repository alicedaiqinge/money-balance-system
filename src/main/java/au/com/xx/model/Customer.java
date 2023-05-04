package au.com.xx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    int id;
    String name;
    Double balance;

    String accountNumber;
}
