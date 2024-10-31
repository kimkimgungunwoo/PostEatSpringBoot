package com.example.posteatsptingboot.domain.BoardLike.Service;


import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.Board.Repository.BoardRepository;
import com.example.posteatsptingboot.domain.BoardLike.Dto.BoardLikeInfo;
import com.example.posteatsptingboot.domain.BoardLike.Dto.BoardLikeRequest;
import com.example.posteatsptingboot.domain.BoardLike.Entity.BoardLike;
import com.example.posteatsptingboot.domain.BoardLike.Repository.BoardLikeRepository;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Repository.UserRepository;
import com.example.posteatsptingboot.domain.User.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor

public class BoardLikeService {

    private final BoardLikeRepository boardLikeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository UserRepository;

    public BoardLikeInfo addBoardLike(BoardLikeRequest request){
        Board board=boardRepository.findById(request.getBoard_id()).orElse(null);
        User user=UserRepository.findById(request.getUser_id()).orElse(null);

        if(board==null||user==null){
            throw new IllegalArgumentException("board or User is null");
        }
        boolean alreadyLike=boardLikeRepository.existsByBoardLikeUserAndLikedBoard(user,board);
        if(alreadyLike){
            throw new IllegalArgumentException("Board like already exist");
        }
        BoardLike boardLike=BoardLike.builder().BoardLikeUser(user).LikedBoard(board).build();
        boardLikeRepository.save(boardLike);
        return BoardLikeInfo.of(boardLike);

    }





}
