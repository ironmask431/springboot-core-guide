package kevin.springboot.core.guide.batch.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kevin.springboot.core.guide.entity.User;
import kevin.springboot.core.guide.enums.UserRole;
import kevin.springboot.core.guide.jwt.JwtTokenProvider;
import kevin.springboot.core.guide.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //mockMvc 사용하기 위해 적용
@Transactional // 테스트 과정에서 DB 변경내역 모두 rollback 처리
class TestControllerTest {
    //mockMvc : 서블릿컨테이너 구동없이 가상의 MVC환경에서 모의 http 서블릿을 요청하는 유틸클래스.
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;
    private HttpHeaders httpHeaders;
    private User user;

    @BeforeEach
    void init() {
        //테스트용 유저 생성후 저장
        user = User.builder()
                   .id(1L)
                   .email("test@naver.com")
                   .name("테스트")
                   .password("1234")
                   .roles(List.of(UserRole.ADMIN))
                   .build();
        userRepository.save(user);

        //테스트 유저 토큰 생성
        String token = jwtTokenProvider.createToken(user);

        //토큰 헤더에 저장
        httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
    }

    @Test
    @DisplayName("getRequestDto API 검증")
    void getRequestDto() throws Exception {
        //given
        String name = "kevin";
        String email = "kevin@naver.com";
        String createDateTime = LocalDateTime.now().toString();

        //when & then
        mockMvc.perform(get("/test/request-dto")
                       .headers(httpHeaders) // mockMvc 요청시 토큰이 추가된 헤더를 적용해준다.
                       .param("name", name)
                       .param("email", email)
                       .param("createDateTime", createDateTime))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.response.name").value(name))
               .andExpect(jsonPath("$.response.email").value(email))
               .andExpect(jsonPath("$.response.createDateTime").exists())
               .andDo(print());
    }
}