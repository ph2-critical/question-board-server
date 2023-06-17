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
}
