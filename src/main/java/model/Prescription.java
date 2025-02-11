package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Prescription {

    private String id;
    private String diagnosis;
    private String medicine;
    private String dosage;
    private String duration;
    private String doctorId;
    private String patientId;

}