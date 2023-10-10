package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @ManyToOne
    @JsonIgnore
    private Position position;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("date ASC")
    private List<TimeSheet> timeSheets;

    public Employee(String name, int age, Position position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

}