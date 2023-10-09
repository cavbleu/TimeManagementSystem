package ru.egartech.tmsystem.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValidationEnum {

    REQUIRED("Имя депортамента обязательно для заполнения");
    private final String description;

}
