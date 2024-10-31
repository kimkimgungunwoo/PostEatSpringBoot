package com.example.posteatsptingboot.domain.Postit.Dto;


import com.example.posteatsptingboot.domain.Postit.Entity.PostIt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PostitInfo {

    private Long postit_id;
    private Long board_id;
    private Long user_id;
    private String user_name;
    private String address;
    private String content;
    private int like;

    public static PostitInfo of(PostIt entity){
        return PostitInfo.builder().
                address(entity.getAddress()).
                content(entity.getContent()).
                postit_id(entity.getId()).
                user_id(entity.getUser().getId()).
                user_name(entity.getUser().getNickname()).
                board_id(entity.getBoard().getId()).build();

    }

}
