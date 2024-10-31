package com.example.posteatsptingboot.domain.User.Contolloer;


import com.example.posteatsptingboot.domain.Board.Dto.BoardInfo;
import com.example.posteatsptingboot.domain.Board.Service.BoardService;
import com.example.posteatsptingboot.domain.Follow.Dto.FollowRequest;
import com.example.posteatsptingboot.domain.Follow.Dto.FollowingInfo;
import com.example.posteatsptingboot.domain.Follow.Service.FollowService;
import com.example.posteatsptingboot.domain.Postit.Dto.PostitInfo;
import com.example.posteatsptingboot.domain.Postit.Service.PostItService;
import com.example.posteatsptingboot.domain.User.Dto.UserCreateRequest;
import com.example.posteatsptingboot.domain.User.Dto.UserInfo;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final BoardService boardService;
    private final PostItService postItService;
    private final FollowService followService;

    @PostMapping()
    public UserInfo createUser(@RequestBody UserCreateRequest request){
        return userService.CreateUser(request);
    }

    @GetMapping()
    public List<UserInfo> getAllUsers(){
        return userService.GetAllUsers();
    }

    @GetMapping("/{user_id}")
    public UserInfo getUserById(@PathVariable("user_id") Long id){
        return userService.GetUserInfoById(id);
    }

    @GetMapping("/search")
    public List<UserInfo> searchUserByName(@RequestParam String NickName){
        return userService.GetUserInfosByNickname(NickName);

    }

    @GetMapping("/{user_id}/boards")
    public List<BoardInfo> GetBoardsByUserId(@PathVariable("user_id") Long userId){
        return boardService.GetBoardsByUserId(userId);
    }

    @GetMapping("/{user_id}/postits")
    public List<PostitInfo> GetPostitsByUserId(@PathVariable("user_id") Long userId){
        return postItService.GetPostItsByUserId(userId);
    }

    @PostMapping("/{user_id}/follow")
    public FollowingInfo followUser(@PathVariable("user_id") Long userId, @RequestParam Long Id){
        FollowRequest followRequest=FollowRequest.builder()
                .follower_id(Id).followee_id(userId).build();
        return followService.addFollow(followRequest);

    }

    @GetMapping("/{UserId}/following")
    public List<UserInfo> GetFollowingByUserId(@PathVariable("UserId") Long userId){
        return userService.getFollowingInfo(userId);
    }

    @GetMapping("/{UserId}/follower")
    public List<UserInfo> GetFollowerByUserId(@PathVariable("UserId") Long userId){
        return userService.getFollowerInfo(userId);
    }

    @GetMapping("/{UserId}/star")
    public List<BoardInfo> GetStarredBoardByUserId(@PathVariable("UserId") Long userId){
        return boardService.getStarredBoardByUserId(userId);
    }

    @GetMapping("{UserId}/like_board")
    public List<BoardInfo> GetLikedBoardsByUserId(@PathVariable Long UserId){
        return boardService.getLikedBoardByUserId(UserId);
    }

    @GetMapping("{UserId}/like_postit")
    public List<PostitInfo> GetLikedPostitsByUserId(@PathVariable Long UserId){
        return postItService.getLikedPostItsByUserId(UserId);
    }










}
