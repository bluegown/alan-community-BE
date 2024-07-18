package hello.kcs_assignment.repository;

import hello.kcs_assignment.model.Posts;
import hello.kcs_assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Posts, Integer> {

    @Query("SELECT p FROM Posts p WHERE p.postId = :postId")
    Optional<Posts> findByPostId(@Param("postId") int postId);




    long count();
}

