package com.example.posteatsptingboot.domain.Star.Dto;


import com.example.posteatsptingboot.domain.Star.Entity.Star;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StarInfo {

    private Long board_id;
    private String board_name;
    private Long user_id;
    private String user_name;

    public static StarInfo of(Star entity){
        return StarInfo.builder().
                board_id(entity.getStarredBoard().getId()).
                board_name(entity.getStarredBoard().getTitle()).
                user_id(entity.getStarredUser().getId()).
                user_name(entity.getStarredUser().getNickname())
                .build();


    }

}
