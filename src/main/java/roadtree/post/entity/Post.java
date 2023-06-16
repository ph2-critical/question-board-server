package roadtree.post.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import roadtree.post.entity.type.Category;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 100, nullable = false)// 추후 타입으로 표현
    private String nickname;

    @Column(length = 255, nullable = false)
    private String password;

    @Column
    private Category categoryId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Long views;

    @Column
    private Long likes;

    @Column
    private Long dislikes;

    @Column
    private Long meToos;

    @Column
    private Long comments;

    @Column
    //댓글 총 갯수
    private Long commentCount;

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


}

