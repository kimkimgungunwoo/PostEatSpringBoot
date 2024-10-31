package com.example.posteatsptingboot.domain.Board.Contolloer;


import com.example.posteatsptingboot.domain.Board.Dto.BoardCreateRequest;
import com.example.posteatsptingboot.domain.Board.Dto.BoardInfo;
import com.example.posteatsptingboot.domain.Board.Service.BoardService;
import com.example.posteatsptingboot.domain.BoardLike.Dto.BoardLikeInfo;
import com.example.posteatsptingboot.domain.BoardLike.Dto.BoardLikeRequest;
import com.example.posteatsptingboot.domain.BoardLike.Service.BoardLikeService;
import com.example.posteatsptingboot.domain.Postit.Dto.PostitInfo;
import com.example.posteatsptingboot.domain.Postit.Service.PostItService;
import com.example.posteatsptingboot.domain.Star.Dto.StarInfo;
import com.example.posteatsptingboot.domain.Star.Dto.StarRequest;
import com.example.posteatsptingboot.domain.Star.Service.StarService;
import com.example.posteatsptingboot.domain.User.Dto.UserInfo;
import com.example.posteatsptingboot.domain.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;
    private final StarService starService;
    private final BoardLikeService boardLikeService;
    private final UserService userService;
    private final PostItService postItService;

    @PostMapping()
    public BoardInfo createBoard(@RequestBody BoardCreateRequest request) {
        return boardService.createBoard(request);
    }

    @GetMapping()
    public List<BoardInfo> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("/{board_id}")
    public BoardInfo getBoardById(@PathVariable Long board_id) {
        return boardService.getBoardById(board_id);
    }
    @GetMapping("/search")
    public List<BoardInfo> searchBoard(@RequestParam String keyword) {
        return boardService.getBoardsByTitle(keyword);
    }

    @PostMapping("/{board_id}/star")
    public StarInfo starBoard(@PathVariable Long board_id , @RequestParam Long user_id) {
        StarRequest starRequest=StarRequest.builder().
                user_id(user_id).board_id(board_id).build();

        return starService.addStar(starRequest);
    }

    @GetMapping("/{board_id}/star")
    public List<UserInfo> GetStarredUsers(@PathVariable Long board_id) {
        return userService.getStarredUserInfoByBoardId(board_id);
    }

    @PostMapping("/{BoardId}/like")
    public BoardLikeInfo likeBoard(@PathVariable Long BoardId,@RequestParam Long UserId) {
        return boardLikeService.addBoardLike(BoardLikeRequest.builder().user_id(UserId).board_id(BoardId).build());
    }

    @GetMapping("/{BoardId}/like")
    public List<UserInfo> GetLikedUsers(@PathVariable Long BoardId) {
        return userService.getLikedUserInfoByBoardId(BoardId);
    }

    @GetMapping("/{BoardId}/postit")
    public List<PostitInfo> getPostitInfoListByBoardId(@PathVariable Long BoardId) {
        return postItService.GetPostItsByBoardId(BoardId);
    }




}
