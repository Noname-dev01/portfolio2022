package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;
import portfolio2022.portfolio2022.dailyshop.domain.entity.CartItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.CartService;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;

    /**
     * 카트 페이지 접속
     */
    @GetMapping("/mypage/cart/{id}")
    public String memberCartPage(@PathVariable("id")Long id, Model model,
                                 @AuthenticationPrincipal MemberDetails memberDetails){
        //로그인 되어있는 유저의 id 와 카트 조회하는 id가 같은지 확인
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            Member member = memberService.findMember(id);
            //로그인 유저의 카트 가져오기
            Cart memberCart = member.getCart();

            //카트에 들어있는 아이템 모두 가져오기
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);

            //카트에 들어있는 상품들의 총 가격
            int totalPrice = 0;
            for (CartItem cartItem : cartItemList) {
                totalPrice += cartItem.getCount() * cartItem.getProduct().getPrice();
            }

            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("totalCount",memberCart.getCount());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member",member);

            return "dailyshop/memberCart";
        }else {
         return "redirect:/dailyShop/main";
        }
    }
}
