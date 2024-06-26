package kevin.springboot.core.guide.api.controller;

import io.swagger.v3.oas.annotations.Hidden;
import kevin.springboot.core.guide.annotation.UserActionLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
@Hidden // swagger 문서에 표시하지 않는다.
public class ExceptionController {

    @GetMapping("/runtime-exception")
    @UserActionLog
    public void goRuntimeException(){
        throw new RuntimeException();
    }

    @GetMapping("/index-exception")
    @UserActionLog
    public void goIndexOutBoundException(){
        throw new IndexOutOfBoundsException();
    }
}
