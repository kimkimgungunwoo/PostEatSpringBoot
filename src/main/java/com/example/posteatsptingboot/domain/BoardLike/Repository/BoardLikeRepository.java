package com.example.posteatsptingboot.domain.BoardLike.Repository;

import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.BoardLike.Entity.BoardLike;
import com.example.posteatsptingboot.domain.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardLikeRepository extends JpaRepository<BoardLike,Long> {
    boolean existsByBoardLikeUserAndLikedBoard(User user, Board board);
}
