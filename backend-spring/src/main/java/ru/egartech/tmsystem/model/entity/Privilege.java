package ru.egartech.tmsystem.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
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
    @Column(name = "name", nullable = false, length = 120)
    private String name;
    @Column(name = "increased_amount", nullable = false)
    @PositiveOrZero(message = "{number.only.positive}")
    private Long increasedAmount;
}


