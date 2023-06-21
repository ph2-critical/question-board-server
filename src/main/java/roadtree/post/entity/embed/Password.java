package roadtree.post.entity.embed;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Password {

    @Column(length = 255, nullable = false)
    private String password;

    public Password(String password) {
        this.password = password;
    }


    // 패스워드를 빈칸으로 받지 않는다.
    public boolean checkPassword() {
        if (this.password.isEmpty()) return false;
        return true;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호 칸은 비어있을 수 없습니다.");
        }
        if (password.length() < 4 || password.length() > 12) {
            throw new IllegalArgumentException("패스워드 길이는 최소 4자리~12자리 사이여야합니다.");
        }
        // Additional password validation rules can be added here
        this.password = password;
    }
}
