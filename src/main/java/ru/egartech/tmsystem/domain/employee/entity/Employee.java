package ru.egartech.tmsystem.domain.employee.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.domain.department.entity.Department;
import ru.egartech.tmsystem.domain.position.entity.Position;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "age", nullable = false)
    private int age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employees")
    private Position position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employees")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employee_time_sheet",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "time_sheet_id"))
    @JsonIgnoreProperties("employees")
    private List<TimeSheet> timeSheet;

    public Employee(String fullName, int age, Department department) {
        this.fullName = fullName;
        this.age = age;
        this.department = department;
    }

}