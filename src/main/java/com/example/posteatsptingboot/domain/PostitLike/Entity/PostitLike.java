package com.example.posteatsptingboot.domain.PostitLike.Entity;


import com.example.posteatsptingboot.domain.Postit.Entity.PostIt;
import com.example.posteatsptingboot.domain.User.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="postitlike")
public class PostitLike {
    @Id
    @GeneratedValue
    @JoinColumn(name="postitlike_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="postitlike_user")
    private User postitLikeuser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="liked_postit")
    private PostIt likedPostIt;

    @Builder
    public PostitLike(User user, PostIt postIt){
        this.postitLikeuser = user;
        this.likedPostIt = postIt;
    }



}

