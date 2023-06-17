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

    //닉네임과 비밀번호가 입력되어 있지 않으면 댓글을 작성할 수 없다.
    public boolean checkNickNameAndPassword(NickName nickName, Password password) {
        if (nickName.checkNickName()) return false;
        if (password.checkPassword()) return false;
        return true;
    }
}
