package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Appointment {

    private String id;
    private String date;
    private String time;
    private Integer number;
    private String status;
    private String doctorId;
    private String patientId;
    private String sessionId;

}