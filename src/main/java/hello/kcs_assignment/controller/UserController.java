package hello.kcs_assignment.controller;

import hello.kcs_assignment.DTO.UsersDTO;
import hello.kcs_assignment.model.User;
import hello.kcs_assignment.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.HTML;
import java.util.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/user/info")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/join") // 여기서부터 다시하기
    public ResponseEntity<String> join(@RequestParam("userId") String userId,
                                       @RequestParam("password") String password,
                                       @RequestParam("nickname") String nickname,
                                       @RequestParam("image") MultipartFile image){


        if (!image.isEmpty()) {


            userService.registerUserWithImage(userId,password,nickname,image);
        }
        else{
            return ResponseEntity.status(500).body("Failed to upload image.");
        }

        return ResponseEntity.ok("Successfully Joined!");

    }
    @GetMapping("/profileImage")
    public User profileImage(HttpSession session){
        String userId = (String)session.getAttribute("userId");
        return userService.findByUserId(userId);
    }
    @PostMapping("fixNickname")
    public User fixNickName(HttpSession session, @RequestParam("nickname") String nickname,
                            @RequestParam("image") MultipartFile image){
        String userId = (String)session.getAttribute("userId");
        return userService.fixNickName(userId,nickname,image);

    }
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        return ResponseEntity.ok(userId);
    }
    @GetMapping("/userId/{userId}")
    public User getUserById(@PathVariable("userId") String userId, HttpSession session) {
        return userService.findByUserId(userId);
    }
    @PostMapping("/removeUser")
    public User deleteUserById(HttpSession session){
        String userId = (String) session.getAttribute("userId");
        return userService.deleteByUserId(userId);
    }



}

