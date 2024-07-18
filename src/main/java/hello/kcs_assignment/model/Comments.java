package hello.kcs_assignment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comments {
    @Id
    @Column(name = "comment_number", nullable = false, unique = true)
    private int commentNumber; // 인식 됨

    @Column(name = "postid", nullable = false)
    private int postId; // 아니 여기있는 postid는 인식 되는데 왜?

    @Column(name = "userid", nullable = false)
    private String userId; // 모르겠음


    @Column(name = "dates", nullable = false)
    private String dates; // 모르겠음

    @Column(name = "comment_detail", nullable = false)
    private String comment_detail; // 인식 안됨

    @Column(name = "nickname", nullable = false)
    private String nickname;

    public int getCommentNumber() {
        return commentNumber;
    }


    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getComment_detail() {
        return comment_detail;
    }

    public void setComment_detail(String comment_detail) {
        this.comment_detail = comment_detail;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}
