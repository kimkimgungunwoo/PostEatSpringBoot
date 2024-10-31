package com.example.posteatsptingboot.domain.Postit.Dto;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class PostitCreateRequest {
    private String address;

    private String content;

    private Long user_id;

    private Long board_id;
}
