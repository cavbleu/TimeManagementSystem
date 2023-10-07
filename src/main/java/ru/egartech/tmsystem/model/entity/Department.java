package ru.egartech.tmsystem.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "name")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH}, orphanRemoval = true)
    @JsonIgnore
    private List<Position> positions = new ArrayList<>();

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.DETACH, CascadeType.REFRESH}, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }
}
