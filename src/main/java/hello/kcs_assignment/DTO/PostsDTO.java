package hello.kcs_assignment.DTO;

import org.springframework.web.multipart.MultipartFile;

public class PostsDTO {
    private long postId;
    private String title;
    private int likeCount;
    private int commentCount;
    private int viewCount;
    private String nickname;
    private String userId;
    private String dates;
    private String postDetail;
    private MultipartFile postImage;

    // 기본 생성자
    public PostsDTO() {
    }

    // 모든 필드를 포함한 생성자
    public PostsDTO(long postId, String title, int likeCount, int commentCount, int viewCount, String nickname, String userId, String dates, String postDetail, MultipartFile postImage) {
        this.postId = postId;
        this.title = title;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.nickname = nickname;
        this.userId = userId;
        this.dates = dates;
        this.postDetail = postDetail;
        this.postImage = postImage;
    }

    // getter 및 setter 메서드
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getPostDetail() {
        return postDetail;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public MultipartFile getPostImage() {
        return postImage;
    }

    public void setPostImage(MultipartFile postImage) {
        this.postImage = postImage;
    }
}