package com.example.posteatsptingboot.domain.Star.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StarRequest {
    private Long board_id;
    private Long user_id;

}

