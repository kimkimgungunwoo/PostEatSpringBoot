package com.example.posteatsptingboot.domain.PostitLike.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class PostitLikeRequest {
    private Long postit_id;
    private Long user_id;

    @Builder
    public PostitLikeRequest(Long postit_id, Long user_id) {
        this.postit_id = postit_id;
        this.user_id = user_id;
    }

}
