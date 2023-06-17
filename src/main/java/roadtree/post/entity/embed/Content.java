package roadtree.post.entity.embed;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Content {
    private String content;

    public Content(String content) {
        this.content = content;
    }
}
