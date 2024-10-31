package com.example.posteatsptingboot.domain.Postit.Entity;


import com.example.posteatsptingboot.domain.Board.Entity.Board;
import com.example.posteatsptingboot.domain.PostitLike.Entity.PostitLike;
import com.example.posteatsptingboot.domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor
@Table(name="postit")
@Setter
public class PostIt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="postit_id",nullable = false)
    private Long id;

    @Column(name="content")
    private String content;

    @Column(name="adress")
    private String address;

    @OneToMany(mappedBy = "likedPostIt",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<PostitLike> postitLikes=new HashSet<>();

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @Builder
    public PostIt(Long id,String content,String address,User user,Board board)
    {
        this.id=id;
        this.content=content;
        this.address=address;
        this.user=user;
        this.board=board;

    }
}
