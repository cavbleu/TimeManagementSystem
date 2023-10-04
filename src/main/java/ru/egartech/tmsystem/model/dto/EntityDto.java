package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EntityDto {

    private Long id;
    private String name;
}
