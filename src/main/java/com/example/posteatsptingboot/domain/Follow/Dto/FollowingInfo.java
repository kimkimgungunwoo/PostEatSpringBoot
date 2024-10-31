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
public class FollowingInfo {

    private Long following_id;
    private String following_name;
    private int follower;
    private int following;


    public static FollowingInfo of(Follow entity) {
        return FollowingInfo.builder()
                .following_id(entity.getFollowee().getId()).
                following_name(entity.getFollowee().getNickname()).
                follower(entity.getFollowee().getFollowers().size()).
                following(entity.getFollowee().getFollowees().size())
                .build();

    }

}
