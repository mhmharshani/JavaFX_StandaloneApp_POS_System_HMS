package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicalRecord {

    private String id;
    private String date;
    private String diagnosis;
    private String treatment;
    private String patientId;

}
