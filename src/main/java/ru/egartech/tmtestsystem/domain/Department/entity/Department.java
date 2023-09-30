package ru.egartech.tmtestsystem.domain.Department.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmtestsystem.domain.Employee.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Employee> employees = new ArrayList<>();

    public Department(String names) {
        this.name = name;
    }
}
