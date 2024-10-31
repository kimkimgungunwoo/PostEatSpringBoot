package com.example.posteatsptingboot.domain.Postit.Repository;

import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.Postit.Entity.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostitRepositroy extends JpaRepository<PostIt, Long>
{
    List<PostIt> findByBoardId(Long board_id);
    List<PostIt> findByUserId(Long user_id);

}
