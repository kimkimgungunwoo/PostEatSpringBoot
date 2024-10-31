package com.example.posteatsptingboot.domain.PostitLike.Repository;

import com.example.posteatsptingboot.domain.Postit.Entity.PostIt;
import com.example.posteatsptingboot.domain.PostitLike.Entity.PostitLike;
import com.example.posteatsptingboot.domain.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostitLikeRepository extends JpaRepository<PostitLike, Long> {
    boolean existsByPostitLikeuserAndLikedPostIt(User user, PostIt postIt);
    Optional<PostitLike> findByPostitLikeuserAndLikedPostIt(User user,PostIt postIt);

}
