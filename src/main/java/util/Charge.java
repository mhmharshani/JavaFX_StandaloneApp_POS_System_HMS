package util;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor

public enum Charge {
    HOSPITALFEE(500.00),DOCTORFEE(1500.00),BOOKINGFEE(150.00),DISCOUNT(0.1),SCANFEE(2000.00);

    private Double fee;

}
