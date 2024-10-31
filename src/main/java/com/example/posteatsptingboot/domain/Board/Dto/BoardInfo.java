package com.example.posteatsptingboot.domain.Board.Dto;

import com.example.posteatsptingboot.domain.Board.Entity.Board;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardInfo {

    private Long user_id;
    private String user_name;
    private String title;
    private Long board_id;
    private int star;
    private int like;


    public static BoardInfo of(Board entity){
        return BoardInfo.builder().
                user_id(entity.getUser().getId()).
                user_name(entity.getUser().getNickname()).
                title(entity.getTitle()).
                board_id(entity.getId()).
                star(entity.getStars().size()).
                like(entity.getBoardLiked().size()).
                build();
    }
}
