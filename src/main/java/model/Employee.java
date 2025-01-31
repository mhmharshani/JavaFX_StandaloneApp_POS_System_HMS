package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Employee {

    private String id;
    private String name;
    private String gender;
    private String address;
    private String phoneNo;
    private String designation;
    private String qualification;
    private Double salary;

}
