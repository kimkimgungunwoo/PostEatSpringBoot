package com.example.posteatsptingboot.domain.Board.Entity;


import com.example.posteatsptingboot.domain.BoardLike.Entity.BoardLike;
import com.example.posteatsptingboot.domain.Star.Entity.Star;
import com.example.posteatsptingboot.domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id",nullable=false)
    private Long id;

    @Column(name="board_title",nullable=false)
    private String title;

//    @Column(name="board_star")
//    private int star=0;
//
//    @Column(name="board_like")
//    private int like=0;

    @ManyToOne
    @JoinColumn(name="user_id") //db의 user_id로 설정된 id를 찾아서 저장
    private User user;

    @OneToMany(mappedBy = "starredBoard",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<Star> stars=new HashSet<>();

    @OneToMany(mappedBy = "likedBoard",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<BoardLike> boardLiked=new HashSet<>();

    @Builder
    public Board(String title,User user) {
        this.title = title;
        this.user = user;

    }
}
