package roadtree.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roadtree.comment.dto.respone.CommentResponseDto;
import roadtree.comment.entity.Comment;
import roadtree.comment.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentResponseDto.CreatedComment createComment(Comment comment) {
        Comment save = commentRepository.save(comment);
        return new CommentResponseDto.CreatedComment(save.getCommentId());
    }

    public boolean deleteComment(Long id, String nickName, String password) {
        Comment comment = commentById(id);
        if(comment.getNickName().equals(nickName) && comment.getPassword().equals(password)){
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    private Comment commentById(Long id) {
        return commentRepository.findById(id).orElseThrow
                (() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
    }

    public List<Comment> findComments(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return  comments;
    }
}
