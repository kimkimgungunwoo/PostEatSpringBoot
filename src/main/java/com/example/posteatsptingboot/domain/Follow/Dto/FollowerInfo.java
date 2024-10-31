package com.example.posteatsptingboot.domain.Follow.Dto;


import com.example.posteatsptingboot.domain.Follow.Entity.Follow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowerInfo {
    private Long follower_id;
    private String follower_name;
    private int follower;
    private int following;


    public static FollowerInfo of(Follow entity){
        return FollowerInfo.builder()
                .follower_id(entity.getFollower().getId())
                .follower_name(entity.getFollower().getNickname())
                .follower(entity.getFollower().getFollowers().size())
                .following(entity.getFollower().getFollowees().size())
                .build();
    }

}
