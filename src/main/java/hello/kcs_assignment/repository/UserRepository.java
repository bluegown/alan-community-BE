package hello.kcs_assignment.repository;
import hello.kcs_assignment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserId(String userId);
    User deleteByuserId(String userId);
}
