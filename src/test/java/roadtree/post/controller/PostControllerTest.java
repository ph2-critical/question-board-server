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
import roadtree.comment.dto.request.CommentRequestDto;
import roadtree.comment.dto.respone.CommentResponseDto;
import roadtree.post.dto.request.PostRequestDto;
import roadtree.post.dto.respone.PostResponseDto;
import roadtree.post.entity.Category;
import roadtree.post.entity.Post;
import roadtree.post.entity.embed.*;
import roadtree.post.mapper.PostMapper;
import roadtree.post.service.PostService;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@ExtendWith({RestDocumentationExtension.class})
//@ContextConfiguration(classes = {PostController.class})
@AutoConfigureRestDocs
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private PostMapper mapper;


    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    public void findPost() throws Exception {
        //given

        // 들어오는 값
        Long requestPostId = 1L;

        //만들어지는 게시글
        Post post = new Post();
        post.setPostId(1L);
        post.setNickName(new NickName("닉네임"));
        post.setPassword(new Password("비밀번호"));
        post.setCategory(Category.NOTICE); //공지
        post.setTitle(new Title("제목"));
        post.setContent(new Content("내용"));
        post.setPostInfo(new PostInfo());
        post.setNotice(true);
        post.setCreatedAt("2021-08-01");

        // 나와야하는 최종 결과값
        PostResponseDto.FindPost findPost = new PostResponseDto.FindPost();
        findPost.setId(1L);
        findPost.setNickName("닉네임");
        findPost.setTitle("제목");
        findPost.setContent("내용");
        findPost.setCategoryId(1);
        findPost.setCreatedDate("2021-08-01");
        findPost.setPostInfo(new PostInfo());
        findPost.setCommentCount(0);
        ;
        ArrayList<CommentResponseDto.Comment> comments = new ArrayList<>();
        CommentResponseDto.Comment comment1 = new CommentResponseDto.Comment();
        comment1.setId(1L);
        comment1.setNickName("닉네임2");
        comment1.setContent("댓글 내용");
        comment1.setCreatedAt("2021-08-01");
        CommentResponseDto.Comment comment2 = new CommentResponseDto.Comment();
        comment2.setId(2L);
        comment2.setNickName("닉네임1");
        comment2.setContent("댓글 내용");
        comment2.setCreatedAt("2021-08-01");
        comments.add(comment1);
        comments.add(comment2);
        findPost.setCommentList(comments);

        given(postService.getPostById(Mockito.any(Long.class)))
                .willReturn(post);
        given(mapper.findPostToDto(Mockito.any(Post.class)))
                .willReturn(findPost);

        //when
        // 게시글 생성 요청을 보내고, 응답 확인
        ResultActions actions
                = mockMvc.perform(get("/api/v1/posts/{id}", requestPostId)
                        .accept(MediaType.APPLICATION_JSON)
//                .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andDo(
                        document("post/findPost",
                                pathParameters(
                                        parameterWithName("id").description("게시글 번호")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시글 번호"),
                                        fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("categoryId").type(JsonFieldType.NUMBER).description("카테고리 번호"),
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                        fieldWithPath("createdDate").type(JsonFieldType.STRING).description("게시글 생성 날짜"),
                                        fieldWithPath("postInfo").type(JsonFieldType.OBJECT).description("게시글 정보"),
                                        fieldWithPath("postInfo.views").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("postInfo.likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("postInfo.dislikes").type(JsonFieldType.NUMBER).description("싫어요 수"),
                                        fieldWithPath("postInfo.meToo").type(JsonFieldType.NUMBER).description("나도궁 수"),
                                        fieldWithPath("commentCount").type(JsonFieldType.NUMBER).description("댓글 개수"),
                                        fieldWithPath("commentList").type(JsonFieldType.ARRAY).description("댓글 목록"),
                                        fieldWithPath("commentList[].id").type(JsonFieldType.NUMBER).description("댓글 번호"),
                                        fieldWithPath("commentList[].nickName").type(JsonFieldType.STRING).description("댓글 작성자 닉네임"),
                                        fieldWithPath("commentList[].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                        fieldWithPath("commentList[].createdAt").type(JsonFieldType.STRING).description("댓글 작성 날짜")
                                )
                        )
                );


    }

    //게시글 검색
    @Test
    public void searchPost() throws Exception {
        //given
        //들어가는 값
        String keyword = "검색어";

        // 나와야하는 값
        List<PostResponseDto.SearchPost> list = new ArrayList<>();
        PostResponseDto.SearchPost searchPost = new PostResponseDto.SearchPost();
        searchPost.setId(1L);
        searchPost.setTitle("제목");
        searchPost.setContent("내용");
        searchPost.setNickName("닉네임");
        searchPost.setCategoryId(2);
        searchPost.setCreatedDate("2021-08-01");
        searchPost.setPostInfo(new PostInfo(100L, 1L, 0L, 3L));
        searchPost.setCommentCount(1);
        list.add(searchPost);

        given(postService.searchPosts(Mockito.anyString()))
                .willReturn(new ArrayList<>());
        given(mapper.postListToDtoList(Mockito.anyList())).willReturn(list);

        //when
        ResultActions actions
                = mockMvc.perform(get("/api/v1/posts/search")
                .param("keyword", keyword)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        actions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andDo(document("post/searchPosts",
                                queryParameters(
                                        parameterWithName("keyword").description("검색어")
                                ),
                                responseFields(
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("게시글 번호"),
                                        fieldWithPath("[].title").type(JsonFieldType.STRING).description("제목"),
                                        fieldWithPath("[].content").type(JsonFieldType.STRING).description("내용"),
                                        fieldWithPath("[].categoryId").type(JsonFieldType.NUMBER).description("카테고리 번호"),
                                        fieldWithPath("[].nickName").type(JsonFieldType.STRING).description("작성자 닉네임"),
                                        fieldWithPath("[].createdDate").type(JsonFieldType.STRING).description("게시글 생성 날짜"),
                                        fieldWithPath("[].postInfo").type(JsonFieldType.OBJECT).description("게시글 정보"),
                                        fieldWithPath("[].postInfo.views").type(JsonFieldType.NUMBER).description("조회수"),
                                        fieldWithPath("[].postInfo.likes").type(JsonFieldType.NUMBER).description("좋아요 수"),
                                        fieldWithPath("[].postInfo.dislikes").type(JsonFieldType.NUMBER).description("싫어요 수"),
                                        fieldWithPath("[].postInfo.meToo").type(JsonFieldType.NUMBER).description("나도궁 수"),
                                        fieldWithPath("[].commentCount").type(JsonFieldType.NUMBER).description("댓글 개수")
                                )
                        )
                );

    }


    // 게시글 삭제
    @Test
    public void deletePost() throws Exception {
        //given
        // 들어오는 값
        Long requestPostId = 1L;
        // 결과값
        PostRequestDto.DeletePost deletePost = new PostRequestDto.DeletePost();
        deletePost.setNickName("닉네임");
        deletePost.setPassword("비밀번호");


        given(postService.deletePost(Mockito.any(Long.class), Mockito.anyString(), Mockito.anyString()))
                .willReturn(true);
        //when
        // 게시글 생성 요청을 보내고, 응답 확인
        ResultActions actions
                = mockMvc.perform(delete("/api/v1/posts/{id}", requestPostId)
                .content(objectMapper.writeValueAsString(deletePost))
                .accept(MediaType.APPLICATION_JSON)

                .contentType(MediaType.APPLICATION_JSON)
        );

        actions
                .andExpect(status().isNoContent())
                .andDo(
                        document("post/deletePost",
                                pathParameters(
                                        parameterWithName("id").description("게시글 번호")
                                )

                        )
                );
    }



}