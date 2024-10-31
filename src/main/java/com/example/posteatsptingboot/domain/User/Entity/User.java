package com.example.posteatsptingboot.domain.User.Entity;


import com.example.posteatsptingboot.domain.BoardLike.Entity.BoardLike;
import com.example.posteatsptingboot.domain.Follow.Entity.Follow;
import com.example.posteatsptingboot.domain.PostitLike.Entity.PostitLike;
import com.example.posteatsptingboot.domain.Star.Entity.Star;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="users") //h2는 user가 예약어라서 user로 하면 오류난다네요
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id",unique = true, nullable = false)
    private Long id;

    @Column(name="nickname")
    private String nickname;

    @Column(name="password")
    private String password;


    /*
    *  db에 save하면 JPA가 알아서 매핑해줌
    * */
    @OneToMany(mappedBy = "followee" ,cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<Follow> followers=new HashSet<>();

    @OneToMany(mappedBy = "follower",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<Follow> followees=new HashSet<>();

    @OneToMany(mappedBy = "starredUser" ,  cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<Star> stars=new HashSet<>();

    @OneToMany(mappedBy = "boardLikeUser",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<BoardLike> boardLikes=new HashSet<>();

    @OneToMany(mappedBy = "postitLikeuser",cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Set<PostitLike> postitLikes=new HashSet<>();

    @Column(name="email")
    private String email;

    @Builder
    public User(String nickname, String password, String email) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }


}
