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
}
