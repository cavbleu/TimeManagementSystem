package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import ru.egartech.tmsystem.model.entity.Employee;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "position")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE,
    CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees = new ArrayList<>();

    public Position(String names) {
        this.name = name;
    }

}
