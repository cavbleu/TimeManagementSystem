package ru.egartech.tmsystem.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "department")
@ToString(exclude = {"positions"})
@EqualsAndHashCode(exclude = {"positions"})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, name = "name", length = 60)
    @Pattern(regexp = "[Я-аА-яa-zA-Z\\s]*$", message = "{name.only.letters}")
    @Pattern(regexp = "^\\S+(?: \\S+)*$", message = "{name.start.end.no.spaces}")
    @Size(min = 2, message = "Наименование должно быть не менее 2 символов")
    @Size(max = 60, message = "Наименование должно быть не более 60 символов")
    private String name;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    @OrderColumn(name = "id")
    private List<Position> positions = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }
}
