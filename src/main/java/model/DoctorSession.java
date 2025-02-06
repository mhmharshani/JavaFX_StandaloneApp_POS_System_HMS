package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class DoctorSession {

    private String id;
    private String name;
    private String date;
    private String time;
    private String numberLimit;
    private String status;
    private String doctorId;
}