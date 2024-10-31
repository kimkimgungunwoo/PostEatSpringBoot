package com.example.posteatsptingboot.domain.Board.Service;

import com.example.posteatsptingboot.domain.Board.Dto.BoardCreateRequest;
import com.example.posteatsptingboot.domain.Board.Dto.BoardInfo;
import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.Board.Repository.BoardRepository;
import com.example.posteatsptingboot.domain.Postit.Dto.PostitInfo;
import com.example.posteatsptingboot.domain.User.Dto.UserInfo;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Repository.UserRepository;
import com.example.posteatsptingboot.domain.User.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public BoardInfo createBoard(BoardCreateRequest boardCreateRequest) {
        User FindUser=userRepository.findById(boardCreateRequest.getUser_id()).orElse(null); ;

        Board board = Board.builder().title(boardCreateRequest.getTitle()).
                user(FindUser).build();
        boardRepository.save(board);

        return BoardInfo.of(board);
    }

    public List<BoardInfo> getBoards() {
        List<BoardInfo> boardInfos=new ArrayList<>();
        for(Board board:boardRepository.findAll()){
            boardInfos.add(BoardInfo.of(board));
        }
        return boardInfos;

    }

    public BoardInfo getBoardById(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            return null;
        }
        return BoardInfo.of(board);
    }

    public List<BoardInfo> getBoardsByTitle(String title) {
        List<Board> boards=boardRepository.findByTitleContains(title);
        List<BoardInfo> boardInfos=new ArrayList<>();
        for(Board board:boards){
            boardInfos.add(BoardInfo.of(board));
        }

        return boardInfos;
    }

    public List<BoardInfo> GetBoardsByUserId(Long user_id) {
        List<BoardInfo> boardInfos=new ArrayList<>();
        List<Board> boards=boardRepository.findByUserId(user_id);
        for(Board board:boards){
            boardInfos.add(BoardInfo.of(board));
        }

        return boardInfos;
    }




    public List<BoardInfo> getLikedBoardByUserId(Long userId){
        User user=userRepository.findById(userId).orElse(null);
        if(user==null){
            return null;
        }

        return user.getBoardLikes().stream().map(boardLike -> boardLike.getLikedBoard()).map(BoardInfo::of)
                .collect(Collectors.toList());
    }

    public List<BoardInfo> getStarredBoardByUserId(Long UserId){
        User user=userRepository.findById(UserId).orElse(null);
        if(user==null){
            return null;
        }
        return user.getStars().stream().map(star->star.getStarredBoard()).map(BoardInfo::of).collect(Collectors.toList());
    }







}
