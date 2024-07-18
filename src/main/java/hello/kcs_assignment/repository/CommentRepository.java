package hello.kcs_assignment.repository;

import hello.kcs_assignment.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {
    List<Comments> findByPostId(int postId);

    @Query("SELECT p FROM Comments p WHERE p.commentNumber = :commentNumber")
    Optional<Comments> findByCommentNumber(@Param("commentNumber") int commentNumber);
    @Query("SELECT COALESCE(MAX(commentNumber), 0) AS maxCommentNumber FROM Comments WHERE postId = :postId")
    int findMaxCommentNumber(@Param("postId") int postId);

    void deleteByPostIdAndCommentNumber(int postId, int commentNumber);

}
