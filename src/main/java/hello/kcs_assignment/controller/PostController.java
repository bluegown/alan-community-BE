package hello.kcs_assignment.controller;

import hello.kcs_assignment.DTO.PostsDTO;
import hello.kcs_assignment.DTO.UsersDTO;
import hello.kcs_assignment.model.Posts;
import hello.kcs_assignment.model.User;
import hello.kcs_assignment.repository.PostRepository;
import hello.kcs_assignment.service.PostService;
import hello.kcs_assignment.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Posts> getPost(@PathVariable int postId) {
        // postId는 URL 경로에서 받아온 값입니다
        Optional<Posts> post = postService.getPostById(postId);
        return post.map(posts -> new ResponseEntity<>(posts, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/allposts")
    public ResponseEntity<List<Posts>> getAllPosts() {
        List<Posts> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PostMapping("/submit")
    public Posts submitPost(@RequestParam("title") String title,
                            @RequestParam("post_detail") String postDetail,
                            @RequestParam("post_image") MultipartFile postImage,
                            HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("로그인을 해주세요.");
        }


        return postService.createPost(title, postDetail, postImage,userId);
    }
    // 게시글 create 인가까지 넣어서 완료

    @PostMapping("/info")
    public Posts modifyPost(@RequestBody Map<String, Object> postRequest,HttpSession session){
        String userId = (String)session.getAttribute("userId");
        int postId = Integer.parseInt(postRequest.get("postId").toString());
        Optional<Posts> findPost = postRepository.findByPostId(postId);

        if (findPost.isEmpty()) {
            throw new RuntimeException("글을 찾을 수 없습니다.");
        }
        if (userId == null) {
            throw new RuntimeException("로그인을 해주세요.");
        }
        Posts posts = findPost.get();
        if(!posts.getUser_id().equals(userId)){
            throw new RuntimeException("본인이 작성한 글이 아닙니다.");
        }


        String title = (String) postRequest.get("title");
        String innerText = (String) postRequest.get("innerText");


        return postService.modifyPost(title,innerText,postId);
    }
    @PostMapping("/removePost")
    public void removePost(@RequestBody Map<String, Object> postRequest,HttpSession session){
        String userId = (String)session.getAttribute("userId");
        int postId = Integer.parseInt(postRequest.get("postId").toString());
        if (userId == null) {
            throw new RuntimeException("로그인을 해야죠!!");
        }
        Optional<Posts> findPost = postRepository.findByPostId(postId);

        if (findPost.isEmpty()) {
            throw new RuntimeException("글을 찾을 수 없습니다.");
        }
        postService.removePost(postId);

    }
}
