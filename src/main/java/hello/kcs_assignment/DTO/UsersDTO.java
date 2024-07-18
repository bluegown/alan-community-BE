package hello.kcs_assignment.DTO;

public class UsersDTO {
    private String userId;
    private String password;
    private String nickname;
    private String image;

    // 기본 생성자
    public UsersDTO() {
    }

    // 모든 필드를 포함한 생성자
    public UsersDTO(String userId, String password, String nickname, String image) {
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
    }

    public UsersDTO(String userId) {
    }

    // getter 및 setter 메서드
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}