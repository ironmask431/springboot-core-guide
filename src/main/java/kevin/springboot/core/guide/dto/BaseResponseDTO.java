package kevin.springboot.core.guide.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Getter
public class BaseResponseDTO<T> {

    public boolean ok = true;

    public int status = HttpStatus.OK.value();

    public T response;

    public ResponsePageDTO page;

    public BaseResponseDTO() {
    }

    public BaseResponseDTO(T response) {
        this.response = response;
    }

    public BaseResponseDTO(String response) {
        this.response = (T)response;
    }

    public BaseResponseDTO(ExceptionResponse response) {
        this.ok = false;
        this.status = response.getCode();
        this.response = (T)response;
    }

    public BaseResponseDTO(Page page) {
        this.response = (T)page.getContent();
        this.page = ResponsePageDTO.builder()
                .total(page.getTotalElements())
                .current(page.getNumber())
                .last(page.getTotalElements() == 0 ? 0 : page.getTotalPages() - 1)
                .build();
    }
}
