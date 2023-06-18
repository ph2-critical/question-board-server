package roadtree.post.dto.request;

import lombok.Getter;
import lombok.Setter;
import roadtree.post.entity.Category;
import roadtree.post.entity.embed.*;

import java.util.List;

public class PostRequestDto {

    @Getter
    @Setter
    public static class CreatePost {
        private String nickName;
        private String password;
        private int categoryId;
        private String title;
        private String content;


        public boolean checkNotice(int categoryId){
            return categoryId == 1;
        }
    }
    public static class FindPost {
        public Long id; // 게시판 번호
    }
    public static class DeletePost {
        public Long id; // 게시판 번호
        public String nickName; // 작성자 닉네임
        public String password; // 작성자 비밀번호

    }

}
