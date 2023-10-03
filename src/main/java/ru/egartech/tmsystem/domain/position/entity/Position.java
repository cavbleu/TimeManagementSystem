package ru.egartech.tmsystem.domain.position.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import ru.egartech.tmsystem.domain.employee.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "position")
    @JsonIgnoreProperties("position")
    private List<Employee> employees = new ArrayList<>();

    public Position(String names) {
        this.name = name;
    }
}
