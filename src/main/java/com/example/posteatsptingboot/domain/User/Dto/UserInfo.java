package com.example.posteatsptingboot.domain.User.Dto;


import com.example.posteatsptingboot.domain.User.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserInfo {

    private Long user_id;
    private String nickname;
    private int follwing;
    private int follower;

    public static UserInfo of(User entity) {
        return UserInfo.builder().
                user_id(entity.getId()).
                nickname(entity.getNickname()).
                follower(entity.getFollowers().size()).
                follwing(entity.getFollowees().size()).
                build();
    }

}
