package com.example.posteatsptingboot.domain.PostitLike.Dto;


import com.example.posteatsptingboot.domain.PostitLike.Entity.PostitLike;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostitLikeInfo {

    private Long user_id;
    private Long postit_id;

    public static PostitLikeInfo of(PostitLike entity){
        return PostitLikeInfo.builder()
                .postit_id(entity.getLikedPostIt().getId()).
                user_id(entity.getPostitLikeuser().getId()).build();
    }
}
