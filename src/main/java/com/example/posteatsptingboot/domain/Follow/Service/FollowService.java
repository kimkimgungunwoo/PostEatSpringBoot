package com.example.posteatsptingboot.domain.Follow.Service;

import com.example.posteatsptingboot.domain.Follow.Dto.FollowRequest;
import com.example.posteatsptingboot.domain.Follow.Dto.FollowerInfo;
import com.example.posteatsptingboot.domain.Follow.Dto.FollowingInfo;
import com.example.posteatsptingboot.domain.Follow.Entity.Follow;
import com.example.posteatsptingboot.domain.Follow.Repository.FollowRepository;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowingInfo addFollow(FollowRequest followRequest) {
        Long followerId = followRequest.getFollower_id();
        Long followeeId = followRequest.getFollowee_id();

        User follower = userRepository.findById(followerId).orElse(null);
        User followee = userRepository.findById(followeeId).orElse(null);

        if (follower == null || followee == null) {
            throw new IllegalArgumentException("Follower or Followee is null");
        }

        boolean alreadyFollowing = followRepository.existsByFollowerIdAndFolloweeId(followerId, followeeId);
        if (alreadyFollowing) {
            throw new IllegalArgumentException("Follower already exists");
        }

        Follow follow = Follow.builder()
                .followee(followee)
                .follower(follower)
                .build();

        followRepository.save(follow);

        return FollowingInfo.of(follow);
    }

    public void unfollow(FollowRequest followRequest) {
        Long followerId = followRequest.getFollower_id();
        Long followeeId = followRequest.getFollowee_id();

        User follower = userRepository.findById(followRequest.getFollower_id()).orElse(null);
        User followee = userRepository.findById(followRequest.getFollowee_id()).orElse(null);

        if (follower == null || followee == null) {
            throw new IllegalArgumentException("Follower or Followee is null");
        }

        Follow follow = followRepository.findByFollowerIdAndFolloweeId(followerId, followeeId)
                .orElseThrow(() -> new IllegalArgumentException("Follow relationship does not exist"));

        followRepository.delete(follow);
    }
}
