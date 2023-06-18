package roadtree.post.dto.respone;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roadtree.post.entity.embed.Content;
import roadtree.post.entity.embed.NickName;
import roadtree.post.entity.embed.PostInfo;
import roadtree.post.entity.embed.Title;

import java.util.List;

public class PostResponseDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreatedPost {
        private Long id;
        //        private String title; //재활용하면 되지않나?
//        private String content; //재활용하면 되지않나?
//        private String category; //재활용하면 되지않나?
//        private String writer; //재활용하면 되지않나?
        private String createdDate;
//        private PostInfo postInfo;
        // 좋아요수, 조회수, 싫어요수, 나도궁수 초기화된 수들
//        private int commentCount; // 댓글 0
//        private List<String> commentList; // 댓글 내용도 없음

    }
    @Getter
    @Setter
    @NoArgsConstructor
    public static class FindPost {
        private Long id;
        private String title;
        private String content;
        private int categoryId;
        private String nickName;
        private String createdDate;
        private PostInfo postInfo;
        //         좋아요수, 조회수, 싫어요수, 나도궁수 초기화된 수들
        private int commentCount;
        private List<Comment> commentList;
    }

    public static class DeletePost {
        //private Long id;
        // 삭제 완료된 응답만해주면 될 듯

    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class Comment{
        private Long id;
        private String nickName;
        private String content;
        private String createdAt;

    }


}
