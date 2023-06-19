package roadtree.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roadtree.comment.dto.request.CommentRequestDto;
import roadtree.comment.dto.respone.CommentResponseDto;
import roadtree.comment.entity.Comment;
import roadtree.comment.mapper.CommentMapper;
import roadtree.comment.service.CommentService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    //댓글 생성
    @PostMapping
    public HttpEntity<?> createComment(@PathVariable Long postId, @RequestBody CommentRequestDto.CreateComment createComment) {

        Comment comment = mapper.createCommentDtoToComment(createComment);
        CommentResponseDto.CreatedComment createdComment = commentService.createComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }
    // 댓글 전체 조회
    @GetMapping
    public HttpEntity<?> findComments(@PathVariable Long postId) {
        List<Comment> totalComment =  commentService.findComments(postId);
        List<CommentResponseDto.Comment> findComments = mapper.commentListToDtoList(totalComment);
        return new ResponseEntity<>(findComments, HttpStatus.OK);
    }



    // 댓글 삭제
    @DeleteMapping({"/{commentId}"})
    public HttpEntity<?> deleteComment(@PathVariable Long postId, @PathVariable Long commentId , @RequestBody CommentRequestDto.DeleteComment deleteComment) {
        if(commentService.deleteComment(commentId ,deleteComment.getNickName(), deleteComment.getPassword())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
