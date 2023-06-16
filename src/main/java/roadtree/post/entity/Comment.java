package roadtree.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private Long postId;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String content;

    @Column
    private String createdAt;

    @Column
    private String deletedAt;

    @Column
    private String deletedBy;
}
