package roadtree.post.entity.embed;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Embeddable 사용한 이유
 *  1.코드의 반복을 줄일 수 있다.(아직 미미한 편)
 *  2.객체로 나누기에 가독성 증가,유지보수
 *  3.유효성 검증하기 편하다.
 *  4.테스트코드 작성하기 좋음
 */

/**
 * @Embeddable 사용시 주의사항
 *  기본 생성자를 꼭 생성해야된다.
 */

@Embeddable
@Getter
@NoArgsConstructor
public class Title {
    private String title;

    public Title(String title) {
        this.title = title;
    }
}
