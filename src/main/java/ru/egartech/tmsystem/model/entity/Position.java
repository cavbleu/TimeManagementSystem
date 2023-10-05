package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("positions")
    @Setter(AccessLevel.NONE)
    private Department department;

    @OneToMany(mappedBy = "position")
    @JsonIgnoreProperties("position")
    @Setter(AccessLevel.NONE)
    private List<Employee> employees = new ArrayList<>();

    public Position(String names) {
        this.name = name;
    }
}
