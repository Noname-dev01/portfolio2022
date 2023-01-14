package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import portfolio2022.portfolio2022.dailyshop.domain.dto.JoinDto;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error",required = false)String error,
                            @RequestParam(value = "exception",required = false)String exception,
                            Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "dailyshop/loginForm";
    }

    @GetMapping("/join")
    public String joinForm(){
        return "dailyshop/joinForm";
    }

    @PostMapping("/join")
    public String join(JoinDto joinDto){

        Member member = joinDto.toEntity();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole("ROLE_USER");
        memberService.join(member);

        return "redirect:/dailyShop/login";
    }
//    @PostMapping("/join")
//    public String join(JoinDto joinDto){
//
//        Member member = joinDto.toEntity();
//        member.setRole("ROLE_USER");
//
//        Member memberEntity = authService.join(member);
//        log.info("memberEntity = {}",memberEntity);
//
//        return "dailyshop/loginForm";
//    }

//    @PostMapping("/join")
//    public String join(@Valid MemberForm form, BindingResult result, DuplicateMemberException e){
//        if (result.hasErrors()){
//            return "dailyshop/joinForm";
//        }
//
//        Member memberForm = Member.builder()
//                .username(form.getUsername())
//                .password(form.getPassword())
//                .name(form.getName())
//                .address(new Address(form.getCity(), form.getStreet(), form.getZipcode()))
//                .build();
//
//        memberService.join(memberForm);
//        return "redirect:/dailyShop";
//    }
//
//    @GetMapping("/login")
//    public String loginForm(Model model){
//        model.addAttribute("member", new MemberForm());
//        return "dailyshop/loginForm";
//    }

}
