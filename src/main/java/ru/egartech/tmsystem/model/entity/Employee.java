package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "age", nullable = false)
    private int age;
    @Column(name = "privileges_number")
    private long privilegesNumber;

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


    public Employee(String name, int age, Department department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

}