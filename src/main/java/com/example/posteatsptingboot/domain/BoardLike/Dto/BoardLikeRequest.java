package com.example.posteatsptingboot.domain.BoardLike.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardLikeRequest {
    private Long board_id;
    private Long user_id;

}


