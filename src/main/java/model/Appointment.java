package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Appointment {

    private String id;
    private Date date;
    private String time;
    private Integer number;
    private String status;
    private String doctorId;
    private String patientId;

}