package portfolio2022.portfolio2022.portfoliomain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

}
