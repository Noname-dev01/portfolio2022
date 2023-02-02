package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.CartService;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class AdminController {

    private final MemberService memberService;
    private final CartService cartService;

    /**
     * 관리자 페이지 메인
     */
    @GetMapping("/admin/home")
    public String adminHome(){

        return "dailyshop/admin/home";
    }
    /**
     * 회원 관리
     */
    @GetMapping("/admin/members")
    public String adminMembers(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "dailyshop/admin/member/list";
    }
    /**
     * 회원 탈퇴
     */
    @GetMapping("/admin/members/delete/{id}")
    public String deleteMember(@PathVariable(value = "id")Long id){
        Cart memberCart = cartService.findMemberCart(id);
        cartService.cartDelete(memberCart.getId());
        memberService.deleteMember(id);

        return "redirect:/dailyShop/admin/members";
    }

    /**
     * 회원 권한 수정 페이지
     */
    @GetMapping("/admin/members/role/modify/{id}")
    public String memberModify(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
            model.addAttribute("member",memberService.findMember(id));
            return "dailyshop/admin/member/detail";
    }

    @PostMapping("/admin/members/role/modify/{id}")
    public String memberModify(@PathVariable("id") Long id,String role){
        memberService.memberRoleChange(id,role);

        return "redirect:/dailyShop/admin/members";
    }

    /**
     * 상품 등록
     */
    @GetMapping("/admin/product/register")
    public String productSaveForm(){
        return "dailyshop/admin/product/register";
    }
}
