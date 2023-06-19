package roadtree.post.mapper;

import org.springframework.stereotype.Component;
import roadtree.comment.dto.request.CommentRequestDto;
import roadtree.comment.dto.respone.CommentResponseDto;
import roadtree.post.dto.request.PostRequestDto;
import roadtree.post.dto.respone.PostResponseDto;
import roadtree.post.entity.Category;
import roadtree.comment.entity.Comment;
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
    public List<CommentResponseDto.Comment> commentListToStringList(List<Comment> commentList){
        ArrayList<CommentResponseDto.Comment> list = new ArrayList<>();
        for(Comment comment : commentList){
            CommentResponseDto.Comment commentDto = new CommentResponseDto.Comment();
            commentDto.setId(comment.getCommentId());
            commentDto.setNickName(comment.getNickName().getNickname());
            commentDto.setContent(comment.getContent().getContent());
            list.add(commentDto);
        }
        return list;
    }

    public List<PostResponseDto.SearchPost> postListToDtoList(List<Post> postList) {
        ArrayList<PostResponseDto.SearchPost> list = new ArrayList<>();
        for(Post post : postList){
            PostResponseDto.SearchPost searchPost = new PostResponseDto.SearchPost();
            searchPost.setId(post.getPostId());
            searchPost.setTitle(post.getTitle().getTitle());
            searchPost.setContent(post.getContent().getContent());
            searchPost.setNickName(post.getNickName().getNickname());
            searchPost.setCategoryId(post.getCategory().getCategoryId());
            searchPost.setCreatedDate(post.getCreatedAt());
            searchPost.setPostInfo(post.getPostInfo());
            searchPost.setCommentCount(post.getCommentsList().size());
            list.add(searchPost);
        }
        return list;
    }


    public Comment commentDtoToComment(CommentRequestDto.CreateComment createComment) {
        Comment comment = new Comment();
        comment.setNickName(new NickName(createComment.getNickName()));
        comment.setPassword(new Password(createComment.getPassword()));
        comment.setContent(new Content(createComment.getContent()));

        return comment;
    }
}
