package ru.egartech.tmsystem.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public abstract class EntityDto {

    private Long id;
    //Наименование дто
    private String name;

    public EntityDto(String name) {
        this.name = name;
    }
}
