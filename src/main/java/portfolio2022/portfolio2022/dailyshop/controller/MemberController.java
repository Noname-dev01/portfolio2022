package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolio2022.portfolio2022.dailyshop.domain.dto.JoinDto;
import portfolio2022.portfolio2022.dailyshop.domain.entity.*;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.CartService;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error",required = false)String error,
                            @RequestParam(value = "exception",required = false)String exception,
                            Model model){
        model.addAttribute("error",error);
        model.addAttribute("exception",exception);
        return "dailyshop/login/loginForm";
    }

    @GetMapping("/join")
    public String joinForm(){
        return "dailyshop/login/joinForm";
    }

    @PostMapping("/join")
    public String join(JoinDto joinDto){

        Member member = joinDto.toEntity();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(Role.USER);
        memberService.join(member);

        return "redirect:/dailyShop/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        return "redirect:/dailyShop";
    }

    /**
     * 마이페이지
     */
    @GetMapping("/mypage/{id}")
    public String myPage(@PathVariable("id")Long id, Model model,@AuthenticationPrincipal MemberDetails memberDetails){
        Long loginMemberId = memberDetails.getMember().getId();
        if (Objects.equals(loginMemberId, id)){
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member", member);
            return "dailyshop/mypage";
        }else {
            return "redirect:/dailyShop";
        }
    }

    /**
     * 회원 정보 수정 페이지
     */
    @GetMapping("/mypage/modify/{id}")
    public String memberModify(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        //로그인 한 유저와 수정 페이지에 접속하는 id가 같아야 함
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member", member);
            return "dailyshop/member-modify";
        }else {
            return "redirect:/dailyShop";
        }
    }

    /**
     * 회원정보 수정
     */
    @PostMapping("/mypage/modify/{id}")
    public String memberModify(@PathVariable("id") Long id,Member member){
        memberService.memberModify(member);

        return "redirect:/dailyShop/mypage/{id}";
    }

    /**
     * 충전하기
     */
    @GetMapping("/mypage/charge/{id}")
    public String chargePoint(@PathVariable("id")Long id,Model model,@AuthenticationPrincipal MemberDetails memberDetails){
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member",member);
            return "dailyshop/mypage-charge";
        }else {
            return "redirect:/dailyShop/login";
        }
    }

    @PostMapping("/mypage/charge/{id}")
    public String chargePoint(@PathVariable("id")Long id,int amount){
        memberService.mypageCharge(id,amount);

        return "redirect:/dailyShop/mypage/charge/list";
    }

    /**
     * 충전 요청한 리스트
     */
    @GetMapping("/mypage/charge/list")
    public String chargeList(@AuthenticationPrincipal MemberDetails memberDetails,Model model){
        Long loginId = memberDetails.getMember().getId();
        Member member = memberService.findMember(loginId);
        List<ChargeList> chargeLists = memberService.memberChargeList(member.getId());
        Cart memberCart = cartService.findMemberCart(member.getId());
        List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
        int totalPrice = cartService.cartTotalPrice(member.getId());

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalCount", memberCart.getCount());
        model.addAttribute("cartListCount", cartItemList.size());
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("chargeLists",chargeLists);
        model.addAttribute("member",member);
        return "dailyshop/mypage-charge-list";
    }
    /**
     * 고객센터
     */
    @GetMapping("/mypage/qna/list")
    public String qnaList(@AuthenticationPrincipal MemberDetails memberDetails,Model model){
        Long loginId = memberDetails.getMember().getId();
        Member member = memberService.findMember(loginId);

        model.addAttribute("member", member);
        return "posts-list";
    }
}

