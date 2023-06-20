package roadtree.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import roadtree.audit.Auditable;
import roadtree.comment.entity.Comment;
import roadtree.post.entity.embed.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends Auditable {

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

    @Column
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

