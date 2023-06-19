package roadtree.comment.dto.request;

import lombok.Getter;
import lombok.Setter;

public class CommentRequestDto {

    @Getter
    @Setter
    public static class CreateComment {
        public String nickName;
        public String password;
        public String content;
    }
    @Getter
    @Setter
    public static class DeleteComment {
        public String nickName;
        public String password;
    }


}
