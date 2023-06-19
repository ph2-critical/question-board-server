package roadtree.comment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import roadtree.comment.dto.request.CommentRequestDto;
import roadtree.comment.dto.respone.CommentResponseDto;
import roadtree.comment.entity.Comment;
import roadtree.comment.mapper.CommentMapper;
import roadtree.comment.service.CommentService;
import roadtree.post.mapper.PostMapper;
import roadtree.post.service.PostService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CommentController.class)
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService service;

    @MockBean
    private CommentMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createComment() throws Exception {
        //given
        // 들어오는 값
        CommentRequestDto.CreateComment createComment = new CommentRequestDto.CreateComment();
        createComment.setNickName("닉네임");
        createComment.setPassword("비밀번호");
        createComment.setContent("댓글 내용");
        // 결과값
        CommentResponseDto.CreatedComment createdComment = new CommentResponseDto.CreatedComment();
        createdComment.setId(1L);

        // dto -> entity

        given(mapper.createCommentDtoToComment(Mockito.any(CommentRequestDto.CreateComment.class))).willReturn(new Comment());
        //entity 저장 -> dto
        given(service.createComment(Mockito.any(Comment.class))).willReturn(createdComment);

        //when
        ResultActions actions = mockMvc.perform(post("/api/v1/posts/{postId}/comments", 1L)
                .content(objectMapper.writeValueAsString(createComment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        ;
        //then
        actions.andExpect(status().isCreated())
                .andDo(
                        document("comment/createComment",
                                pathParameters(
                                        parameterWithName("postId").description("게시글 아이디")
                                ),
                                requestFields(
                                        fieldWithPath("nickName").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
                                        fieldWithPath("content").type(JsonFieldType.STRING).description("댓글 내용")
                                ),
                                responseFields(
                                        fieldWithPath("id").type(JsonFieldType.NUMBER).description("댓글 아이디")
                                )
                        )
                );

    }

    @Test
    void findComments() throws Exception {
        //given
        //들어오는 값
        Long postId = 1L;

        //결과값
        List<CommentResponseDto.Comment> findComments = new ArrayList<>();
        for (long i = 1; i < 3; i++) {
            CommentResponseDto.Comment comment = new CommentResponseDto.Comment();
            comment.setId(i);
            comment.setNickName("닉네임" + i);
            comment.setContent("댓글 내용" + i);
            comment.setCreatedAt("2021-08-08");
            findComments.add(comment);
        }

        given(service.findComments(Mockito.anyLong())).willReturn(new ArrayList<>());
        given(mapper.commentListToDtoList(Mockito.any(ArrayList.class))).willReturn(findComments);

        //when
        ResultActions actions = mockMvc.perform(get("/api/v1/posts/{postId}/comments", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(findComments)));
        //then
        actions.andDo(print());
        actions.andExpect(status().isOk()).andDo(print())
                .andDo(
                        document("comment/findComments",
                                pathParameters(
                                        parameterWithName("postId").description("게시글 아이디")
                                ),
                                responseFields(
                                        fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("댓글 아이디"),
                                        fieldWithPath("[].nickName").type(JsonFieldType.STRING).description("닉네임"),
                                        fieldWithPath("[].content").type(JsonFieldType.STRING).description("댓글 내용"),
                                        fieldWithPath("[].createdAt").type(JsonFieldType.STRING).description("댓글 작성일")
                                )
                        )
                );
    }
    @Test
    void deleteComment() throws Exception {
        //given
        //들어오는 값
        Long postId = 1L;
        Long commentId = 1L;
        CommentRequestDto.DeleteComment deleteComment = new CommentRequestDto.DeleteComment();
        deleteComment.setNickName("닉네임");
        deleteComment.setPassword("비밀번호");

        given(service.deleteComment(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString())).willReturn(true);


        //when
        ResultActions actions = mockMvc.perform(delete("/api/v1/posts/{postId}/comments/{commentId}", postId, commentId)
                .content(objectMapper.writeValueAsString(deleteComment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        actions.andExpect(status().isNoContent())
                .andDo(
                        document("comment/deleteComment",
                                pathParameters(
                                        parameterWithName("postId").description("게시글 아이디"),
                                        parameterWithName("commentId").description("댓글 아이디")
                                )
                        )
                );
    }
}
