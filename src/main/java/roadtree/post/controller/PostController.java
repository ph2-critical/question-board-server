package roadtree.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roadtree.post.dto.respone.PostResponseDto;
import roadtree.post.mapper.PostMapper;
import roadtree.post.dto.request.PostRequestDto;
import roadtree.post.entity.Post;
import roadtree.post.service.PostService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper mapper;
    //전체 조회, 최신순 조회,

    @PostMapping()
    public HttpEntity<?> createBoard(@RequestBody PostRequestDto.CreatePost createPost){

        Post post = mapper.createPostDtoToPost(createPost);
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED );
    }

    //
    @GetMapping
    public HttpEntity<?> findPost(@PathVariable Long id) {
        Post findpost = postService.getPostById(id);
        PostResponseDto.FindPost findPost = mapper.findPostToDto(findpost);
        return ResponseEntity.ok(findPost);
    }

    // 검색 기능
    @GetMapping("/search")
    public ResponseEntity<List<Post>> searchPosts(@RequestParam String keyword) {
        List<Post> searchResult = postService.searchPosts(keyword);
        return ResponseEntity.ok(searchResult);
    }

}
