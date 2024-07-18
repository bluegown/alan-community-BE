package hello.kcs_assignment.service;

import hello.kcs_assignment.model.Posts;
import hello.kcs_assignment.model.User;
import hello.kcs_assignment.repository.PostRepository;
import hello.kcs_assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    private String uploadDir ="/Users/sunghyun/Desktop/kcs-assignment/src/main/resources/static/images/";
    public List<Posts> getAllPosts() {
        return postRepository.findAll();
    }
    public Optional<Posts> getPostById(int postId) {
        return postRepository.findById(postId);
    }
    @Transactional
    public Posts createPost(String title, String innerText, MultipartFile photo,String userId) {


        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        } // 만약 userOpt가 비어있다면 throw error를 실행해준다
        User user = userOpt.get();
        Posts posts = new Posts();
        String fileName = photo.getOriginalFilename();
        File dest = new File(uploadDir + fileName);
        try {
            String filePath = uploadDir + '/' + photo.getOriginalFilename();
            Files.write(Paths.get(filePath), photo.getBytes());
            photo.transferTo(dest);
            String filePath2 = "images/" + photo.getOriginalFilename();
            posts.setPost_image(filePath2);

        } catch (IOException e) {
            e.printStackTrace();
        }


        posts.setPostId(postRepository.count() + 1);
        posts.setTitle(title);
        posts.setLike_count(0);
        posts.setComment_count(0);
        posts.setView_count(0);
        posts.setNickname(user.getNickname());
        posts.setPost_detail(innerText);
        posts.setDates(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        posts.setUser_id(String.valueOf(userId));

        return postRepository.save(posts);
    }
    @Transactional
    public Posts modifyPost(String title,String innerText,int postId){
        Optional<Posts> findPost = postRepository.findByPostId(postId);

        if (findPost.isEmpty()) {
            throw new RuntimeException("글을 찾을 수 없습니다.");
        }


        Posts posts = findPost.get();


        posts.setTitle(title);
        posts.setPost_detail(innerText);

        return postRepository.save(posts);


    }

    @Transactional
    public void removePost(int postId) {
        Optional<Posts> findPost = postRepository.findByPostId(postId);

        if (findPost.isEmpty()) {
            throw new RuntimeException("글을 찾을 수 없습니다.");
        }

        postRepository.deleteById(postId);
    }
}
