package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@EqualsAndHashCode(exclude = {"timeSheets", "rests", "distractions"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 60)
    @Pattern(regexp = "[Я-аА-яa-zA-Z\\s]*$", message = "{name.emp.only.letters}")
    @Pattern(regexp = "^\\S+(?: \\S+)*$", message = "{name.emp.start.end.no.spaces}")
    @Size(min = 2, message = "Имя должно быть не менее 10 символов")
    @Size(max = 60, message = "Имя должно быть не более 60 символов")
    private String name;
    @Column(name = "age", nullable = false)
    @Min(value = 18, message = "{Минимальный возраст сотрудника 18}")
    private int age;
    @Column(name = "privileges_number")
    private Long privilegesNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Position position;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("date ASC")
    @JsonIgnore
    private List<TimeSheet> timeSheets = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("date ASC")
    @OrderColumn(name = "id")
    @JsonIgnore
    private List<Rest> rests = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("date ASC")
    @OrderColumn(name = "id")
    @JsonIgnore
    private List<Distraction> distractions = new ArrayList<>();

    public Employee(String name, int age, Position position, Long privilegesNumber, Long id) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.privilegesNumber = privilegesNumber;
        this.id = id;
    }

    public Employee(Long id, String name, int age, Long privilegesNumber, Position position, List<TimeSheet> timeSheets) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.privilegesNumber = privilegesNumber;
        this.position = position;
        this.timeSheets = timeSheets;
    }

    public Employee(String name, int age, Position position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }
}