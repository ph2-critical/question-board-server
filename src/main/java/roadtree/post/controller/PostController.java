package roadtree.post.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import roadtree.post.dto.request.PostRequestDto;
import roadtree.post.dto.respone.PostResponseDto;

@Controller
@RequestMapping("/post")
public class PostController {


    @PostMapping
    public ResponseEntity<?> createdPost(@RequestBody PostRequestDto.CreatePost createPost) {
        PostResponseDto.CreatedPost createdPost = new PostResponseDto.CreatedPost(1L, "2021-09-09");
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }



}
