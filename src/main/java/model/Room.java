package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room {

    private String id;
    private String type;
    private String capacity;
    private String availability;
    private String patientId;

}
