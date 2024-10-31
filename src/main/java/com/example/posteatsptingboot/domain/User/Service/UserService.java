package com.example.posteatsptingboot.domain.User.Service;


import com.example.posteatsptingboot.domain.Board.Dto.BoardInfo;
import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.Board.Repository.BoardRepository;
import com.example.posteatsptingboot.domain.Follow.Entity.Follow;
import com.example.posteatsptingboot.domain.Postit.Dto.PostitInfo;
import com.example.posteatsptingboot.domain.Postit.Entity.PostIt;
import com.example.posteatsptingboot.domain.Postit.Repository.PostitRepositroy;
import com.example.posteatsptingboot.domain.PostitLike.Entity.PostitLike;
import com.example.posteatsptingboot.domain.Star.Repository.StarRepository;
import com.example.posteatsptingboot.domain.User.Dto.UserCreateRequest;
import com.example.posteatsptingboot.domain.User.Dto.UserInfo;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final StarRepository starRepository;
    private final PostitRepositroy postitRepositroy;

    public UserInfo CreateUser(UserCreateRequest request){
        User user=User.builder().email(request.getEmail()).password(request.getPassword1())
                .nickname(request.getNickname()).build();
        userRepository.save(user);

        return UserInfo.of(user);
    }

    public UserInfo GetUserInfoById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null; // or throw a custom exception
        }
        return UserInfo.of(user);
    }




    public List<UserInfo> GetAllUsers(){
        List<UserInfo> userInfos=new ArrayList<>();
        for(User user:userRepository.findAll()){
            userInfos.add(UserInfo.of(user));
        }
        return userInfos;
    }

    public List<UserInfo> GetUserInfosByNickname(String nickname) {
        return userRepository.findByNicknameContains(nickname)
                .stream()
                .map(UserInfo::of)
                .collect(Collectors.toList());
    }

    public List<UserInfo> getFollowerInfo(Long UserId){
        User user=userRepository.findById(UserId).orElse(null);
        if(user==null){
            return null;
        }

        return user.getFollowers().stream().
                map(follow->follow.getFollower())
                .map(UserInfo::of)
                .collect(Collectors.toList());
    }

    public List<UserInfo> getFollowingInfo(Long UserId){
        User user=userRepository.findById(UserId).orElse(null);
        if(user==null){
            return null;
        }

        return user.getFollowees().stream()
                .map(follow -> follow.getFollowee())
                .map(UserInfo::of)
                .collect(Collectors.toList());

    }





    public List<UserInfo> getLikedUserInfoByBoardId(Long BoardId){
        Board board=boardRepository.findById(BoardId).orElse(null);
        if(board==null){
            return null;
        }

        return board.getBoardLiked().stream().
                map(boardLike -> boardLike.getBoardLikeUser())
                .map(UserInfo::of).collect(Collectors.toList());
    }

    public List<UserInfo> getStarredUserInfoByBoardId(Long BoardId){
        Board board=boardRepository.findById(BoardId).orElse(null);
        if(board==null){
            return null;
        }

        return board.getStars().stream()
                .map(star -> star.getStarredUser())
                .map(UserInfo::of)
                .collect(Collectors.toList());

    }

    public List<UserInfo> GetLikedUserByPostitId(Long PostitId){
        PostIt postit=postitRepositroy.findById(PostitId).orElse(null);
        if(postit==null){
            return null;
        }
        return postit.getPostitLikes().stream().map(postitLike -> postitLike.getPostitLikeuser())
                .map(UserInfo::of).collect(Collectors.toList());
    }

//    public UserInfo IncreaseFollower(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//
//        if (user != null) { // user가 null이 아닌 경우에만 처리
//            user.setFollower(user.getFollower() + 1);
//            userRepository.save(user);
//            return UserInfo.of(user);
//        } else {
//            throw new RuntimeException("User not found with id: " + id); // 적절한 예외 처리
//        }
//    }
//
//    public UserInfo IncreaseFollowing(Long id) {
//        User user = userRepository.findById(id).orElse(null);
//        if (user != null) {
//            user.setFollowing(user.getFollowing() + 1);
//            userRepository.save(user);
//            return UserInfo.of(user);
//        }
//        else {
//            throw new RuntimeException("User not found with id: " + id);
//        }
//    }


}
