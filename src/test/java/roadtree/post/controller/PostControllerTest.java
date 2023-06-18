package roadtree.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import roadtree.post.dto.request.PostRequestDto;
import roadtree.post.dto.respone.PostResponseDto;
import roadtree.post.entity.Category;
import roadtree.post.entity.Post;
import roadtree.post.entity.embed.*;
import roadtree.post.mapper.PostMapper;
import roadtree.post.service.PostService;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {PostController.class})
@AutoConfigureRestDocs
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;


    @MockBean
    private PostService postService;

    @MockBean
    private PostMapper mapper;


    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void findPost() {
        System.out.println("test");
    }

    @Test
    public void createPost() throws Exception {
        // given
        // 게시글 생성에 필요한 JSON 데이터를 작성합니다.
        PostRequestDto.CreatePost requestDto = new PostRequestDto.CreatePost();
        requestDto.setNickName("닉네임");
        requestDto.setPassword("비밀번호");
        requestDto.setCategoryId(1); //공지
        requestDto.setTitle("제목");
        requestDto.setContent("내용");

//        //만들어지는 게시글
//        Post post = new Post();
//        post.setPostId(1L);
//        post.setNickName(new NickName("닉네임"));
//        post.setPassword(new Password("비밀번호"));
//        post.setCategory(Category.NOTICE); //공지
//        post.setTitle(new Title("제목"));
//        post.setContent(new Content("내용"));
//        post.setPostInfo(new PostInfo());
//        post.setNotice(true);


        //나와야하는 최종 결과값
        PostResponseDto.CreatedPost createdPost = new PostResponseDto.CreatedPost();
        createdPost.setId(1L);
        createdPost.setCreatedDate("2021-08-01");


        // JSON 데이터로 변환합니다.
        String requestJson = objectMapper.writeValueAsString(requestDto);

        given(mapper.createPostDtoToPost(Mockito.any(PostRequestDto.CreatePost.class)))
                .willReturn(new Post());
        given(postService.createPost(Mockito.any(Post.class)))
                .willReturn(createdPost);



        //when
        // 게시글 생성 요청을 보내고, 응답 확인
        ResultActions actions
                = mockMvc.perform(post("/api/v1/posts")
                        .accept(MediaType.APPLICATION_JSON)
//                .with(csrf())
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
        );
        // then
        actions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andDo(document("post/createPost",
                        requestFields(
                                // 요청 필드 명세
                                List.of(
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 번호"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("작성자 비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시글 번호"),
                                        fieldWithPath("createdDate").type(JsonFieldType.STRING).description("게시글 생성 날짜")
                                )
                        )// 응답 필드 명세

                ))
                .andReturn();
    }


}