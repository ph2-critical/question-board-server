package roadtree.comment.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roadtree.post.entity.embed.PostInfo;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatedComment{
        private Long id;
    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class Comment{
        private Long id;
        private String nickName;
        private String content;
        private LocalDateTime createdAt;

    }
    @Getter
    @Setter
    public static class DeletedComment{
        private Long id;
    }
}
