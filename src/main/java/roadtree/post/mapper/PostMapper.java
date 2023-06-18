package roadtree.post.mapper;

import org.springframework.stereotype.Component;
import roadtree.post.dto.request.PostRequestDto;
import roadtree.post.dto.respone.PostResponseDto;
import roadtree.post.entity.Category;
import roadtree.post.entity.Comment;
import roadtree.post.entity.Post;
import roadtree.post.entity.embed.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    public Post createPostDtoToPost(PostRequestDto.CreatePost createPost) {
        Post post = new Post();
        post.setNickName(new NickName(createPost.getNickName()));
        post.setPassword(new Password(createPost.getPassword()));
        post.setTitle(new Title(createPost.getTitle()));
        post.setContent(new Content(createPost.getContent()));
        post.setCategory(Category.findCategory(createPost.getCategoryId()));
        post.setPostInfo(new PostInfo());
        post.setNotice(createPost.checkNotice(createPost.getCategoryId()));
        return post;
    }

    public PostResponseDto.FindPost findPostToDto(Post findPost) {
        PostResponseDto.FindPost findPostDto = new PostResponseDto.FindPost();
        findPostDto.setId(findPost.getPostId());
        findPostDto.setTitle(findPost.getTitle().getTitle());
        findPostDto.setContent(findPost.getContent().getContent());
        findPostDto.setNickName(findPost.getNickName().getNickname());
        findPostDto.setCategoryId(findPost.getCategory().getCategoryId());
        findPostDto.setCreatedDate(findPost.getCreatedAt());
        findPostDto.setPostInfo(findPost.getPostInfo());

        findPostDto.setCommentCount(findPost.getCommentsList().size());
        findPostDto.setCommentList(commentListToStringList(findPost.getCommentsList()));

        return findPostDto;
    }
    public List<PostResponseDto.Comment> commentListToStringList(List<Comment> commentList){
        ArrayList<PostResponseDto.Comment> list = new ArrayList<>();
        for(Comment comment : commentList){
            PostResponseDto.Comment commentDto = new PostResponseDto.Comment();
            commentDto.setId(comment.getCommentId());
            commentDto.setNickName(comment.getNickname().getNickname());
            commentDto.setContent(comment.getContent().getContent());
            list.add(commentDto);
        }
        return list;
    }



}
