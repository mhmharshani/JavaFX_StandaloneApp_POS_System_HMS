package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    private String patient_id;
    private String name;
    private String nic;
    private String address;
    private String gender;
    private String phoneNo;
    private Integer age;

}
