package roadtree.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roadtree.post.dto.respone.PostResponseDto;
import roadtree.post.repository.PostRepository;
import roadtree.post.entity.Post;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    final private PostRepository postRepository;

    public PostResponseDto.CreatedPost createPost(Post post) {
        postRepository.save(post);

        return new PostResponseDto.CreatedPost(post.getPostId(), post.getCreatedAt());
    }

    public List<Post> searchPosts(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        //optional null 값 에러 처리

        return optionalPost.orElseThrow
                (() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id=" + id));
    }

}
