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
import portfolio2022.portfolio2022.dailyshop.domain.entity.CartItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.CartService;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class CartController {

    private final CartService cartService;
    private final MemberService memberService;
    private final ProductService productService;

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
            int totalPrice = cartService.cartTotalPrice(id);

            model.addAttribute("totalPrice",totalPrice);
            model.addAttribute("totalCount",memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member",member);

            return "dailyshop/memberCart";
        }else {
         return "redirect:/dailyShop/main";
        }
    }

    /**
     * 카트에 상품 넣기
     */
    @PostMapping("/mypage/cart/{id}/{productId}")
    public String addCartItem(@PathVariable("id") Long id,@PathVariable("productId") Long productId, int amount){

        Member member = memberService.findMember(id);
        Product product = productService.findProduct(productId);

        cartService.addCart(member,product,amount);

        return "redirect:/dailyShop/main";
    }

    /**
     * 카트에서 상품 삭제
     */
    @GetMapping("/mypage/cart/{id}/{cartItemId}/delete")
    public String deleteCartItem(@PathVariable("id") Long id, @PathVariable("cartItemId") Long cartItemId,
                                 Model model,@AuthenticationPrincipal MemberDetails memberDetails){
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            //cartItemId로 카트 상품 찾기
            CartItem cartItem = cartService.findCartItemById(cartItemId);
            //해당 유저의 카트 찾기
            Cart memberCart = cartService.findMemberCart(id);
            //카트 전체 수량 감소
            memberCart.setCount(memberCart.getCount()-cartItem.getCount());
            //장바구니 물건 삭제
            cartService.cartItemDelete(cartItemId);

            return "redirect:/dailyShop/mypage/cart/{id}";
        }else {
            return "redirect:/dailyShop/main";
        }
    }

    /**
     * 카트에서 상품 삭제(메인에 있는 카트) 삭제 후 메인 페이지로 이동
     */
    @GetMapping("/mypage/cart/{id}/{cartItemId}/deleteMain")
    public String deleteCartItemMain(@PathVariable("id") Long id, @PathVariable("cartItemId") Long cartItemId,
                                 Model model,@AuthenticationPrincipal MemberDetails memberDetails){
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            //cartItemId로 카트 상품 찾기
            CartItem cartItem = cartService.findCartItemById(cartItemId);
            //해당 유저의 카트 찾기
            Cart memberCart = cartService.findMemberCart(id);
            //카트 전체 수량 감소
            memberCart.setCount(memberCart.getCount()-cartItem.getCount());
            //장바구니 물건 삭제
            cartService.cartItemDelete(cartItemId);

            return "redirect:/dailyShop/main";
        }else {
            return "redirect:/dailyShop/main";
        }
    }
}
