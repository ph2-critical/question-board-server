package roadtree.post.entity.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class PostInfo {
    @Column
    private Long views;

    @Column
    private Long likes;

    @Column
    private Long dislikes;

    @Column
    private Long meToo;

    // 퍼포먼스 차원
//    @Column
//    댓글 총 갯수
//    private Long commentCount;


    public PostInfo(Long views, Long likes, Long dislikes, Long meToo) {
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.meToo = meToo;
    }
}
