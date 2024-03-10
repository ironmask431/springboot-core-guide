package kevin.springboot.core.guide.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor // RequestParam 값을 dto로 매핑 받기위해서 기본 생성자가 필요하다.
public class MemberDto {
    @Schema(description = "이름", example = "kevin")
    @NotBlank(message = "name 은 필수 입력값 입니다.")
    private String name;

    @Schema(description = "이메일", example = "kevin@naver.com")
    @NotBlank(message = "email 은 필수 입력값 입니다.")
    private String email;

    @Schema(description = "등록시간", example = "2024-03-10T11:30:30")
    private LocalDateTime createDateTime;

    @Builder
    public MemberDto(String name, String email, LocalDateTime createDateTime) {
        this.name = name;
        this.email = email;
        this.createDateTime = createDateTime;
    }
}
