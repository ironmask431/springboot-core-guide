package kevin.springboot.core.guide.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CommonUtil {
    /**
     * List<> 를 Page<> 로 변환
     */
    public static Page<?> convertListToPage(List<?> list, Pageable pageable) {
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        PageRequest pageRequest = PageRequest.of(page, size);

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), list.size());

        if (start > end) {
            start = end;
        }
        return new PageImpl<>(list.subList(start, end), pageRequest, list.size());
    }
}
