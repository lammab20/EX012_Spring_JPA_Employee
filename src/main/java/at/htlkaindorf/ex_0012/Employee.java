package at.htlkaindorf.ex_0012;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name="employees")

public class Employee {

    @JsonAlias("emp_no")
    @Id
    @Column(name="emp_no")//Column ist da um die Namen in der Datenbank zu definieren
    private int employeeNo;

    @Column(name="first_name", length = 14)
    private String firstname;

    @Column(name="last_name", length = 16)
    private String lastname;

    @Column(length = 5)//Größe des Datentypes in der Datenbank Festlegen
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name="birth_date")
    @JsonAlias("birthDate")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name="dept_no")//Foreign Key name festlegen
    private Department department;

}