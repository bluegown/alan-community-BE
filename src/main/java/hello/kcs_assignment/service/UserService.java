package hello.kcs_assignment.service;

import jakarta.transaction.Transactional;
import hello.kcs_assignment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import hello.kcs_assignment.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private String uploadDir ="/Users/sunghyun/Desktop/kcs-assignment/src/main/resources/static/images/";


    @Transactional
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    } // 여기에서 존재한다면 ->

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    public User fixNickName(String userId,String nickname,MultipartFile photo){
        Optional<User> findInfo = Optional.ofNullable(userRepository.findByUserId(userId));

        if (findInfo.isEmpty()) {
            throw new RuntimeException("유저를 찾을 수 없습니다.");
        }
        User user = findInfo.get();

        user.setNickname(nickname);
        String filePath2 = "images/" + photo.getOriginalFilename();
        if(!user.getImage().equals(filePath2)){ // 사진이 갱신된 경우라면 ?
            String fileName = photo.getOriginalFilename();
            File dest = new File(uploadDir + fileName);
            try {
                String filePath = uploadDir + '/' + photo.getOriginalFilename();
                Files.write(Paths.get(filePath), photo.getBytes());
                photo.transferTo(dest);
                user.setImage(filePath2);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return userRepository.save(user);



    }
    public ResponseEntity<String> registerUserWithImage(String userId, String password, String nickname, MultipartFile photo) {
        User user = new User();



            String fileName = photo.getOriginalFilename();
            File dest = new File(uploadDir + fileName);
            try {
                String filePath = uploadDir + '/' + photo.getOriginalFilename();
                Files.write(Paths.get(filePath), photo.getBytes());
                photo.transferTo(dest);
                user.setUserId(userId);
                user.setPassword(password);
                user.setNickname(nickname);
                String filePath2 = "images/" + photo.getOriginalFilename();
                user.setImage(filePath2);


                userRepository.save(user);

                return ResponseEntity.ok(filePath2);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return ResponseEntity.status(500).body("Failed!");

    }
    public User deleteByUserId(String userId){
        return userRepository.deleteByuserId(userId);

    }
}
// userService 부분
