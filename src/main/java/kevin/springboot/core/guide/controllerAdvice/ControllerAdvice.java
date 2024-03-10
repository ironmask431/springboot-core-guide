package kevin.springboot.core.guide.controllerAdvice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "kevin.springboot.core.guide.controller")
public class ControllerAdvice {

    //@GetMapping 컨트롤러에서 RequestParam 을 DTO 로 받기 위한 설정
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }
}
