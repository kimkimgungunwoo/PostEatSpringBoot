package com.example.posteatsptingboot.domain.Follow.Repository;

import com.example.posteatsptingboot.domain.Follow.Entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Long> {

    List<Follow> findByFollowerId(Long id);
    List<Follow> findByFolloweeId(Long id);


    boolean existsByFollowerIdAndFolloweeId(Long followerId, Long followeeId);

    Optional<Follow> findByFollowerIdAndFolloweeId(Long followerId, Long followeeId);
}
