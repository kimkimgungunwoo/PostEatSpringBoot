package com.example.posteatsptingboot.domain.Board.Repository;

import com.example.posteatsptingboot.domain.Board.Entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitleContains(String title);
    List<Board> findByUserId(Long userId);



}

