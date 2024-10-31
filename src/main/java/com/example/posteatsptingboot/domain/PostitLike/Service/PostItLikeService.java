package com.example.posteatsptingboot.domain.PostitLike.Service;


import com.example.posteatsptingboot.domain.Postit.Entity.PostIt;
import com.example.posteatsptingboot.domain.Postit.Repository.PostitRepositroy;
import com.example.posteatsptingboot.domain.PostitLike.Dto.PostitLikeInfo;
import com.example.posteatsptingboot.domain.PostitLike.Dto.PostitLikeRequest;
import com.example.posteatsptingboot.domain.PostitLike.Entity.PostitLike;
import com.example.posteatsptingboot.domain.PostitLike.Repository.PostitLikeRepository;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostItLikeService {

    private final PostitLikeRepository postitLikeRepository;
    private final PostitRepositroy postitRepositroy;
    private final UserRepository userRepository;


    public PostitLikeInfo addPostitLike(PostitLikeRequest request) {
        PostIt postIt=postitRepositroy.findById(request.getPostit_id()).orElse(null);
        User user=userRepository.findById(request.getUser_id()).orElse(null);

        if(postIt==null||user==null){
            throw new IllegalArgumentException("Postit or User not found");
        }

        boolean alreadyLke= postitLikeRepository.existsByPostitLikeuserAndLikedPostIt(user,postIt);
        if(alreadyLke){
            throw new IllegalArgumentException("Postit already exists");
        }
        PostitLike postitLike=PostitLike.builder().user(user).postIt(postIt).build();
        postitLikeRepository.save(postitLike);
        return PostitLikeInfo.of(postitLike);
    }
}
