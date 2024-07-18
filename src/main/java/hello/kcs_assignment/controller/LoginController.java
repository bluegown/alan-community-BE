package hello.kcs_assignment.controller;
import hello.kcs_assignment.model.LoginRequest;
import jakarta.servlet.http.HttpSession;
import hello.kcs_assignment.model.LoginResponse;
import hello.kcs_assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hello.kcs_assignment.service.UserService;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;




    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        String userId = loginRequest.getUserId();
        String password = loginRequest.getPassword();

        User user = userService.findByUserId(userId);

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No exist user or incorrect password");
        }

        // 세션에 사용자 ID를 저장합니다.
        session.setAttribute("userId", userId); // 세션의 userId를 현재 유저아이디로 설정한다

        // JSON 응답 생성
        return ResponseEntity.ok(new LoginResponse(200, "환영합니다, " + userId + "님!", session.getId()));
    }
}
