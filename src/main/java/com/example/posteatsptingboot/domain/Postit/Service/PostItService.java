package com.example.posteatsptingboot.domain.Postit.Service;


import com.example.posteatsptingboot.domain.Board.Repository.BoardRepository;
import com.example.posteatsptingboot.domain.Postit.Dto.PostitCreateRequest;
import com.example.posteatsptingboot.domain.Postit.Dto.PostitInfo;
import com.example.posteatsptingboot.domain.Postit.Entity.PostIt;
import com.example.posteatsptingboot.domain.Postit.Repository.PostitRepositroy;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostItService
{
    private final PostitRepositroy postitRepositroy;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    public PostitInfo CreatePostIt(PostitCreateRequest request){
        PostIt postit=PostIt.builder()
                .address(request.getAddress()).content(request.getContent()).
                board(boardRepository.findById(request.getBoard_id()).orElse(null))
                .user(userRepository.findById(request.getUser_id()).orElse(null)).build();

        postitRepositroy.save(postit);
        return PostitInfo.of(postit);
    }

    public PostitInfo GetPostIt(Long id){
        return PostitInfo.of(postitRepositroy.findById(id).orElse(null));
    }

    public List<PostitInfo> GetAllPostIt(){
        List<PostitInfo> postitInfos=new ArrayList<>();
        List<PostIt> postItList=postitRepositroy.findAll();
        for(PostIt postIt:postItList){
            postitInfos.add(PostitInfo.of(postIt));
        }
        return postitInfos;
    }

    public List<PostitInfo> GetPostItsByUserId(Long userId){
        List<PostitInfo> postitInfos=new ArrayList<>();
        List<PostIt> postItList=postitRepositroy.findByUserId(userId);
        for(PostIt postIt:postItList){
            postitInfos.add(PostitInfo.of(postIt));
        }
        return postitInfos;
    }

    public List<PostitInfo> GetPostItsByBoardId(Long board_id){
        List<PostitInfo> postitInfos=new ArrayList<>();
        List<PostIt> postItList=postitRepositroy.findByBoardId(board_id);
        for(PostIt postIt:postItList){
            postitInfos.add(PostitInfo.of(postIt));
        }
        return postitInfos;
    }




    public List<PostitInfo> getLikedPostItsByUserId(Long userId){
        User user=userRepository.findById(userId).orElse(null);
        if(user==null){
            return null;
        }

        return user.getPostitLikes().stream()
                .map(postitLike -> postitLike.getLikedPostIt()).map(PostitInfo::of).collect(Collectors.toList());


    }



}
