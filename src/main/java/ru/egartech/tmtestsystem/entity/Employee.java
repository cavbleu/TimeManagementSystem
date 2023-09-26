package ru.egartech.tmtestsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "work_position")
    private String workPosition;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("employees")
    private Department department;

    @ManyToMany
    @JoinTable(
            name = "employees_time_management",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "time_management_id"))
    @JsonIgnoreProperties("employees")
    private List<TimeManagement> timeManagement;
//    @ManyToMany
//    @JoinTable(
//            name = "employees_schedule",
//            joinColumns = @JoinColumn(name = "employee_id"),
//            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
//    private List<Schedule> schedule;

    public Employee(String firstName, String lastName, int age, String workPosition, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.workPosition = workPosition;
        this.department = department;
    }

}