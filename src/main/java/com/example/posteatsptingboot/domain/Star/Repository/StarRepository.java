package com.example.posteatsptingboot.domain.Star.Repository;

import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.Star.Entity.Star;
import com.example.posteatsptingboot.domain.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface StarRepository extends JpaRepository<Star, Long> {

    boolean existsByStarredUserAndStarredBoard(User user, Board board);
    Optional<Star> findByStarredUserAndStarredBoard(User user, Board board);
}
