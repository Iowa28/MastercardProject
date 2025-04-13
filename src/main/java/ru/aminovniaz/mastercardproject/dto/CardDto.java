package ru.aminovniaz.mastercardproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDto {

    private Long id;

    private String number;


}
