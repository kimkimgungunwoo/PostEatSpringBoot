package com.example.posteatsptingboot.domain.BoardLike.Entity;


import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name="boardlike")
public class BoardLike {

    @Id
    @GeneratedValue
    @JoinColumn(name="boardlike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardlike_user")
    private User boardLikeUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="liked_board")
    private Board likedBoard;

    @Builder
    public BoardLike(User BoardLikeUser, Board LikedBoard) {
        this.boardLikeUser = BoardLikeUser;
        this.likedBoard = LikedBoard;
    }
}

