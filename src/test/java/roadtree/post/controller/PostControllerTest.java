package roadtree.post.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
import roadtree.post.entity.embed.Content;
import roadtree.post.entity.embed.NickName;
import roadtree.post.entity.embed.Title;
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

@WebMvcTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ContextConfiguration(classes = {PostController.class})
@AutoConfigureRestDocs
class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext ctx;


    @Test
    public void findPost() {
        System.out.println("test");
    }

    @Test
    public void createPost() throws Exception {
        // given
        // 생성에 필요한 요청 데이터
        String requestBody = "{\n" +
                "  \"title\": \"string\",\n" +
                "  \"content\": \"string\",\n" +
                "  \"postInfo\": {\n" +
                "    \"password\": \"string\",\n" +
                "    \"nickname\": \"string\"\n" +
                "  }\n" +
                "}";
        PostResponseDto.FindPost findPost = new PostResponseDto.FindPost(
                1L,
                new Title("title"),
                new Content("content"),
                "category",
                new NickName("nickname"),
                "createdDate",
                null,
                0,
                null);
        //when


        // 게시글 생성 요청을 보내고, 응답 확인
        ResultActions actions
                = mockMvc.perform(post("/post")
                        .accept(MediaType.APPLICATION_JSON)
//                .with(csrf())
                        .content(requestBody)
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
                                        fieldWithPath("content").description("내용"),
                                        fieldWithPath("postInfo.nickname").description("작성자 닉네임"),
                                        fieldWithPath("postInfo.password").description("작성자 비밀번호")
                                )
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("id").description("게시글 번호"),
                                        fieldWithPath("createdDate").description("게시글 생성 날짜")
                                )
                        )// 응답 필드 명세

                ))
                .andReturn();
    }


}