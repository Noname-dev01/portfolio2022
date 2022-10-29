package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.member.Member;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "dailyshop/joinForm";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm){
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setPassword(memberForm.getPassword());

        memberService.join(member);
        return "redirect:/dailyShop";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        return "dailyshop/loginForm";
    }

    @PostMapping("/login")
    public String login(Member member){
        return "redirect:/dailyShop";
    }
}
