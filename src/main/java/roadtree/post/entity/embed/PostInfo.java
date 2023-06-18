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
    private Long views  = 0L ;

    @Column
    private Long likes = 0L;

    @Column
    private Long dislikes = 0L;

    @Column
    private Long meToo = 0L;

    // 퍼포먼스 차원
//    @Column
//    댓글 총 갯수
//    private Long commentCount;
    //필요 없을 수도?
    public PostInfo(Long views, Long likes, Long dislikes, Long meToo) {
        this.views = views;
        this.likes = likes;
        this.dislikes = dislikes;
        this.meToo = meToo;
    }
}
