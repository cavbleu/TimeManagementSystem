package ru.egartech.tmsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeDto extends EntityDto{

    //Увеличенное значение, которое дает привилегия
    private Long increasedAmount;
}
