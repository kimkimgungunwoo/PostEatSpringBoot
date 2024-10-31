package com.example.posteatsptingboot.domain.Postit.Contolloer;


import com.example.posteatsptingboot.domain.Postit.Dto.PostitCreateRequest;
import com.example.posteatsptingboot.domain.Postit.Dto.PostitInfo;
import com.example.posteatsptingboot.domain.Postit.Service.PostItService;
import com.example.posteatsptingboot.domain.PostitLike.Dto.PostitLikeInfo;
import com.example.posteatsptingboot.domain.PostitLike.Dto.PostitLikeRequest;
import com.example.posteatsptingboot.domain.PostitLike.Service.PostItLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/postit")
public class PostItController {

    private final PostItService postItService;
    private final PostItLikeService postItLikeService;

    @PostMapping("/{BoardId}")
    public PostitInfo CreatePostIt(@PathVariable Long BoardId,@RequestBody PostitCreateRequest request) {
        request.setBoard_id(BoardId);
        return postItService.CreatePostIt(request);
    }


    @PostMapping("/{PostitId}/like")
    public PostitLikeInfo PostitLike(@PathVariable Long PostitId, @RequestParam Long UserId) {
        PostitLikeRequest request=PostitLikeRequest.builder().postit_id(PostitId).user_id(UserId).build();
        return postItLikeService.addPostitLike(request);
    }

    @GetMapping("/{UserId}/like")
    public List<PostitInfo> GetPostitLikesByUserId(@PathVariable Long UserId) {
        return postItService.getLikedPostItsByUserId(UserId);
    }







}
