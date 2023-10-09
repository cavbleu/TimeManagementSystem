package ru.egartech.tmsystem.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    @Pattern(regexp = "[Я-аА-яa-zA-Z\\s]*$", message = "{name.only.letters}")
    @Pattern(regexp = "^\\S+(?: \\S+)*$", message = "{name.start.end.no.spaces}")
    @Size(min = 1, message = "Наименование должно быть не менее 1 символа")
    @Size(max = 30, message = "Наименование должно быть не более 30 символов")
    private String name;
    @Column(name = "increased_amount", nullable = false)
    private Long increasedAmount;
}
