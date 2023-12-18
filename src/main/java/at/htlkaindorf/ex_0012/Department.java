package at.htlkaindorf.ex_0012;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(length = 5)
    @JsonAlias("number")
    private String deptNo;

    @Column(length = 48)
    @JsonAlias("name")
    private String deptName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="emp_no")
    private Employee deptManager;
}
