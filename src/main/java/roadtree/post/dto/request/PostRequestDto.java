package roadtree.post.dto.request;

import roadtree.post.entity.Category;
import roadtree.post.entity.embed.*;

import java.util.List;

public class PostRequestDto {
    public static class CreatePost {
        private NickName nickName;
        private Password password;
        private int categoryName;
        private Title title;
        private Content content;
    }
    public static class FindPost {
        public Long id; // 게시판 번호
    }
    public static class DeletePost {
        public Long id; // 게시판 번호
        public NickName nickName; // 작성자 닉네임
        public Password password; // 작성자 비밀번호

    }

}
