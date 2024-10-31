package com.example.posteatsptingboot.domain.Board.Dto;


import com.example.posteatsptingboot.domain.User.Entity.User;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCreateRequest {
    private String title;

    private Long user_id;



}
