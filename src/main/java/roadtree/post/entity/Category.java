package roadtree.post.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import roadtree.post.entity.Post;

@Getter
@AllArgsConstructor
public enum Category {

    NOTICE(1, "공지사항"), FREE(2, "자유게시판"), QNA(3, "질문게시판"), Info(4, "정보게시판");

    private int categoryId;
    private String categoryName;

    public static Category findCategory(int categoryId) {
        for (Category category : Category.values()) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        return null;
    }

}
