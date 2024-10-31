package com.example.posteatsptingboot.domain.BoardLike.Dto;

import com.example.posteatsptingboot.domain.BoardLike.Entity.BoardLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardLikeInfo {

    private Long user_id;
    private Long board_id;

    public static BoardLikeInfo of(BoardLike entity){
        return BoardLikeInfo.builder().
                user_id(entity.getBoardLikeUser().getId()).
                board_id(entity.getLikedBoard().getId()).
                build();
    }
}
