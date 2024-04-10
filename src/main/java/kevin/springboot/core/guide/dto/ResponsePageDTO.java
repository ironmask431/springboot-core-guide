package kevin.springboot.core.guide.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponsePageDTO {
    public long total;
    public long current;
    public long last;

    public ResponsePageDTO() {
    }

    @Builder
    public ResponsePageDTO(long total, long current, long last) {
        this.total = total;
        this.current = current;
        this.last = last;
    }
}
