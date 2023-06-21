package roadtree.post.entity.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class NickName {

    @Column(length = 100, nullable = false)// 추후 타입으로 표현
    private String nickname;


    public NickName(String nickname) {
        this.nickname = nickname;
    }

    // 닉네임 검증 로직을 메서드로 넣어줄 수 있음.
    // 닉네임은 빈칸으로 받지않는다.
    public boolean checkNickName() {
        if (this.nickname.isEmpty()) return false;
        return true;
    }
    public void setNickname(String nickname) {
        if (nickname == null || nickname.trim().isEmpty()) {
            throw new IllegalArgumentException("닉네임 칸은 비어있을 수 없습니다.");
        }
        if (nickname.length() < 3 /*|| nickname.length() > 20*/) {
            throw new IllegalArgumentException("닉네임 길이는 최소 3자리는 넘어야합니다.");
        }
        this.nickname = nickname;
    }
}
