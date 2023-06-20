package roadtree.comment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roadtree.audit.Auditable;
import roadtree.post.entity.Post;
import roadtree.post.entity.embed.Content;
import roadtree.post.entity.embed.NickName;
import roadtree.post.entity.embed.Password;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private Long postId;

    @Embedded
    private NickName nickName;

    @Embedded
    private Password password;

    // 댓글 내용 작성전, 작성자의 닉네임과 비밀번호를 입력받는다.
    @Embedded
    private Content content;

    @Column
    private String deletedAt;

    @Column
    private String deletedBy;

    @ManyToOne
    private Post post;
}
