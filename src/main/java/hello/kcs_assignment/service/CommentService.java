package hello.kcs_assignment.service;

import hello.kcs_assignment.model.Comments;
import hello.kcs_assignment.model.User;
import hello.kcs_assignment.repository.CommentRepository;
import hello.kcs_assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository; // 자동으로 생성자 주입해주는 기능이다.

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public List<Comments> getCommentsByPostId(int postId) {
        return commentRepository.findByPostId(postId);
    }
    @Transactional
    public Comments addComment(int postId,String commentDetail,String userId){
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        } // 만약 userOpt가 비어있다면 throw error를 실행해준다

        User user = userOpt.get();
        int maxCommentNumber = (commentRepository.findMaxCommentNumber(postId));
        int newCommentNumber = maxCommentNumber + 1;
        Comments comments = new Comments();
        comments.setCommentNumber(newCommentNumber);
        comments.setPostId(postId);
        comments.setUserId(userId);
        comments.setDates(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        comments.setComment_detail(commentDetail);
        comments.setNickname(user.getNickname());

        return commentRepository.save(comments);
    }
    @Transactional
    public Comments fixComment(int commentNumber,int postId,String comment_detail,String userId){
        Optional <Comments> CommentInfo = commentRepository.findByCommentNumber(commentNumber);
        // commentNumber로 comment의 한 행을 가져온다
        if(CommentInfo.isEmpty()){
            throw new RuntimeException("댓글 정보를 찾을 수 없습니다.");
        }


        Comments comments = CommentInfo.get();
        if (!Objects.equals(comments.getUserId(), userId)){
            throw new RuntimeException("작성자와 일치하지 않습니다");
        }
        comments.setComment_detail(comment_detail);

        return commentRepository.save(comments);

    }
    @Transactional
    public void removeComment(int postId,int commentNumber){
        Optional <Comments> commentInfo = commentRepository.findByCommentNumber(commentNumber);
        if(commentInfo.isEmpty()){
            throw new RuntimeException("댓글 정보를 찾을 수 없습니다.");
        }

        commentRepository.deleteByPostIdAndCommentNumber(postId,commentNumber);

    } // 댓글 삭제만 구현하면 기본 CRUD는 끝남
}
