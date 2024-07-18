package hello.kcs_assignment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Posts {
    @Id
    @Column(name = "postid", nullable = false, unique = true)
    private long postId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "like_count", nullable = false)
    private int like_count;

    @Column(name = "comment_count", nullable = false)
    private int comment_count;

    @Column(name = "view_count", nullable = false)
    private int view_count;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "dates",nullable = false)
    private String dates;
    // 기본값 지정 + insert 쿼리 나갈때

    @Column(name = "post_detail", nullable = false)
    private String post_detail;

    @Column(name = "post_image")
    private String post_image;
 // 여기 아래로는 getter , setter
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

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getPost_detail() {
        return post_detail;
    }

    public void setPost_detail(String post_detail) {
        this.post_detail = post_detail;
    }

    public String getPost_image() {
        return post_image;
    }

    public void setPost_image(String post_image) {
        this.post_image = post_image;
    }
}
