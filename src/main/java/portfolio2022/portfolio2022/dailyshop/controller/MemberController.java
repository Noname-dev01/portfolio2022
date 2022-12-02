package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.Member;
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
    public String join(@Valid MemberForm memberForm, BindingResult result){

        if (result.hasErrors()){
            return "dailyshop/joinForm";
        }
        Member createMember = MemberForm.createMemberForm(memberForm);

        memberService.join(createMember);
        return "redirect:/dailyShop";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("member", new MemberForm());
        return "dailyshop/loginForm";
    }
}
