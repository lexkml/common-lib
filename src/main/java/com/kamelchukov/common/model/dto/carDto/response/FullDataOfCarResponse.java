package com.kamelchukov.common.model.dto.carDto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullDataOfCarResponse {
    private Long id;
    private String model;
    private String year;
    private String color;
    private Long personId;
    private String firstName;
    private String lastName;
}
