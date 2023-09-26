package ru.egartech.tmtestsystem.entity;

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
    @Column(name = "employee_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "work_position")
    private String workPosition;
    @ManyToOne
    @JoinColumn(name = "employees")
    private Department department;
    @ManyToMany
    @JoinTable(
            name = "employees_time_management",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "time_management_id"))
    private List<TimeManagement> timeManagement;
    @ManyToMany
    @JoinTable(
            name = "employees_schedule",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private List<Schedule> schedule;

    public Employee(String firstName, String lastName, int age,
                    Department department, List<TimeManagement> timeManagement, List<Schedule> schedule) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.department = department;
        this.timeManagement = timeManagement;
        this.schedule = schedule;
    }
}