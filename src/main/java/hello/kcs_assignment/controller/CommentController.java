package hello.kcs_assignment.controller;
import hello.kcs_assignment.model.CommentRequest;
import hello.kcs_assignment.model.Comments;
import hello.kcs_assignment.repository.CommentRepository;
import hello.kcs_assignment.service.CommentService;
import hello.kcs_assignment.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;


    @GetMapping("/comments/{postId}")
    public ResponseEntity<List<Comments>> getCommentsByPostId(@PathVariable int postId) {
        try {
            List<Comments> comments = commentService.getCommentsByPostId(postId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @PostMapping("/addComment")
    public Comments addComment(@RequestBody Map<String, Object> commentRequest, HttpSession session){
        String userId = (String)session.getAttribute("userId");
        String comment_detail = (String) commentRequest.get("commentDetail");
        Integer postId = (Integer) commentRequest.get("postId");
        return commentService.addComment(postId, comment_detail,userId);

    }

    @PostMapping("/fixComment")
    public Comments fixComment(@RequestBody Comments comments,HttpSession session){
        String userId = (String)session.getAttribute("userId");
        return commentService.fixComment(comments.getCommentNumber(),comments.getPostId(),comments.getComment_detail(),userId);
    }

    @PostMapping("/removeComment")
    public ResponseEntity<?> removeComment(@RequestBody Map<String, Object> postRequest,HttpSession session){
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(401).body("로그인을 해야 합니다.");
        }

        int postId = Integer.parseInt(postRequest.get("postId").toString());
        int commentNumber = Integer.parseInt(postRequest.get("commentNumber").toString());

        try {
            commentService.removeComment(postId,commentNumber);
            return ResponseEntity.ok("댓글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    }


