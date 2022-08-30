package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {//model 에 데이타를 실어서 뷰에 넘길 수 있다.
        model.addAttribute("data", "hello!!");//모델이 데이타라는 키의 값인 hello!!를 넘긴다.
        return "hello";//화면 이름
    }
}
