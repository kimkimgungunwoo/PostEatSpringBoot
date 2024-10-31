package com.example.posteatsptingboot.domain.Star.Entity;


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
@Table(name="star")
//@IdClass(StarId.class)
public class Star {

    @Id
    @GeneratedValue
    @Column(name="star_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="starred_user_id")
    private User starredUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Board starredBoard;

    @Builder
    public Star(User user,Board board) {
        this.starredUser = user;
        this.starredBoard = board;
    }

}

