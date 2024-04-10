package kevin.springboot.core.guide.controllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kevin.springboot.core.guide.dto.BaseResponseDTO;
import kevin.springboot.core.guide.dto.ExceptionResponse;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "kevin.springboot.core.guide")
public class ResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof Page){
            return new BaseResponseDTO((Page)body);
        }
        if(body instanceof ExceptionResponse){
            return new BaseResponseDTO((ExceptionResponse)body);
        }
        if(body instanceof String){
            try {
                return new ObjectMapper().writeValueAsString(new BaseResponseDTO((String)body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return new BaseResponseDTO(body);
    }
}

