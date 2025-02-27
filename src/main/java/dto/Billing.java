package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Billing {

    private String id;
    private String genDate;
    private String genTime;
    private String description;
    private Double total;
    private String status;
    private String patientId;

}