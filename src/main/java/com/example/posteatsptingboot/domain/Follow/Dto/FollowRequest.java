package com.example.posteatsptingboot.domain.Follow.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequest {
    private Long follower_id;

    private Long followee_id;

}
