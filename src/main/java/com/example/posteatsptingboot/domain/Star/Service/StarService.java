package com.example.posteatsptingboot.domain.Star.Service;


import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.Board.Repository.BoardRepository;
import com.example.posteatsptingboot.domain.Star.Dto.StarInfo;
import com.example.posteatsptingboot.domain.Star.Dto.StarRequest;
import com.example.posteatsptingboot.domain.Star.Entity.Star;
import com.example.posteatsptingboot.domain.Star.Repository.StarRepository;
import com.example.posteatsptingboot.domain.User.Entity.User;
import com.example.posteatsptingboot.domain.User.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor

public class StarService {
    private final StarRepository starRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public StarInfo addStar(StarRequest request){
        User user=userRepository.findById(request.getUser_id()).orElse(null);
        Board board = boardRepository.findById(request.getBoard_id()).orElse(null);
        if (user==null || board==null){
            throw new IllegalArgumentException("Board or User is null");
        }

        boolean alreadyStarred=starRepository.existsByStarredUserAndStarredBoard(user,board);
        if (alreadyStarred){
            throw new IllegalArgumentException("Board already starred");
        }

        Star star=Star.builder()
                .user(user).board(board).build();

        starRepository.save(star);
        return StarInfo.of(star);
    }

    public void deleteStar(StarRequest request){
        User user=userRepository.findById(request.getUser_id()).orElse(null);
        Board board = boardRepository.findById(request.getBoard_id()).orElse(null);
        if (user==null || board==null){
            throw new IllegalArgumentException("Board or User is null");
        }

        Star star=starRepository.findByStarredUserAndStarredBoard(user,board)
                .orElseThrow(()->new IllegalArgumentException("No star found"));
        starRepository.delete(star);
    }







}
