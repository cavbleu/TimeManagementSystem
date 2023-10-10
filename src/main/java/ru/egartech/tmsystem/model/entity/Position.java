package ru.egartech.tmsystem.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "name", nullable = false, unique = true, length = 60)
    @Pattern(regexp = "[Я-аА-яa-zA-Z\\s]*$", message = "{name.only.letters}")
    @Pattern(regexp = "^\\S+(?: \\S+)*$", message = "{name.start.end.no.spaces}")
    @Size(min = 1, message = "Наименование должно быть не менее 1 символа")
    @Size(max = 60, message = "Наименование должно быть не более 60 символов")
    private String name;

    @ManyToOne
    @JsonIgnore
    private Department department;

    @OneToMany(mappedBy = "position", fetch = FetchType.EAGER)
    @JsonIgnore
    @OrderBy("name ASC")
    private List<Employee> employees = new ArrayList<>();

    public Position(String name) {
        this.name = name;
    }

}
