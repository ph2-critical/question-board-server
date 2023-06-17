package roadtree.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import roadtree.post.entity.embed.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;


    @Embedded
    private NickName nickName;

    @Embedded
    private Password password;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @Embedded
    private PostInfo postInfo;


    @Column
    //게시판작성,
    private String createdAt;

    //최근 댓글 업데이트날짜
    @Column
    private String commentUpdatedAt;

    @Column
    private String deletedAt;

    @Column
    //게시판 삭제 여부
    private String deletedBy;

    @Column
    private boolean notice;

    @OneToOne
    private Category category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentsList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentsList.add(comment);
        comment.setPost(this);
    }

    public void removeComment(Comment comment) {
        commentsList.remove(comment);
        comment.setPost(null);
    }

}

