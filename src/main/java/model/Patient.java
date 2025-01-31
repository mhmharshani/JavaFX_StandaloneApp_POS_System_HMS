package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Patient {

    private String id;
    private String name;
    private String nic;
    private String address;
    private String gender;
    private String phoneNo;
    private Integer age;

}
