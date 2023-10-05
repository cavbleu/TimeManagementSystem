package ru.egartech.tmsystem.model.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrivilegeDto extends EntityDto{

    private Long id;
    private String name;
    private Long increasedAmount;
}
