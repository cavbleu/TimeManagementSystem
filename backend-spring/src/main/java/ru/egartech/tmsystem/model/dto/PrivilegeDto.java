package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrivilegeDto extends EntityDto {

    //Увеличенное значение, которое дает привилегия
    private Long increasedAmount;

    public PrivilegeDto(String name, Long increasedAmount) {
        super(name);
        this.increasedAmount = increasedAmount;
    }
}
