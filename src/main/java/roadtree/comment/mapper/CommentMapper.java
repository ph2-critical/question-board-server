package roadtree.comment.mapper;

import org.springframework.stereotype.Component;
import roadtree.comment.dto.request.CommentRequestDto;
import roadtree.comment.dto.respone.CommentResponseDto;
import roadtree.comment.entity.Comment;
import roadtree.post.entity.embed.Content;
import roadtree.post.entity.embed.NickName;
import roadtree.post.entity.embed.Password;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper{
    public Comment createCommentDtoToComment(CommentRequestDto.CreateComment createComment){
        Comment comment = new Comment();
        comment.setNickName(new NickName(createComment.getNickName()));
        comment.setPassword(new Password(createComment.getPassword()));
        comment.setContent(new Content(createComment.getContent()));
        return comment;
    }

    public CommentResponseDto.Comment CommentToFindComment(Comment comment){
        CommentResponseDto.Comment findComment = new CommentResponseDto.Comment();

        return findComment;
    }


    public List<CommentResponseDto.Comment> commentListToDtoList(List<Comment> totalComment) {
        List<CommentResponseDto.Comment> list = new ArrayList<>();
        for(Comment comment : totalComment){
            CommentResponseDto.Comment commentDto = new CommentResponseDto.Comment();
            commentDto.setId(comment.getCommentId());
            commentDto.setNickName(comment.getNickName().getNickname());
            commentDto.setContent(comment.getContent().getContent());
            commentDto.setCreatedAt(comment.getCreatedAt());
            list.add(commentDto);
        }
        return list;
    }
}
