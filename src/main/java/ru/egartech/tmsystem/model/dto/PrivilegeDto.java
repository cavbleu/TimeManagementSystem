package ru.egartech.tmsystem.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrivilegeDto extends EntityDto{

    //Увеличенное значение, которое дает привилегия
    private Long increasedAmount;
}
