package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;
import portfolio2022.portfolio2022.dailyshop.domain.entity.CartItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.CartService;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
@Slf4j
public class DailyShopController {

    private final ProductService productService;
    private final MemberService memberService;
    private final CartService cartService;

    @GetMapping("/wishlist")
    public String wishlist(){
        return "/dailyshop/wishlist";
    }
    @GetMapping("/product-detail")
    public String productDetail() {
        return "dailyshop/product-detail";
    }

    @GetMapping("/product")
    public String product(){
        return "dailyshop/product";
    }

    /**
     * 로그인 안했을때
     */
    @GetMapping
    public String homePageAnonymous(Model model,@AuthenticationPrincipal MemberDetails memberDetails){
        model.addAttribute("products", productService.findProducts());
        try {
            Long id = memberDetails.getMember().getId();
            model.addAttribute("member",memberService.findMember(id));
        }catch (NullPointerException e){
            return "dailyshop/index";
        }
        return "dailyshop/index";
    }

    /**
     * 로그인 후 이동 화면
     */
    @GetMapping("/main")
    public String home(Model model, @AuthenticationPrincipal MemberDetails memberDetails){

        try {
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member", member);

        }catch (NullPointerException e){
            return "redirect:/dailyShop/login";
        }


        model.addAttribute("products", productService.findProducts());
        return "dailyshop/login/home";
    }
    @GetMapping("/contact")
    public String contact(){
        return "dailyshop/contact";
    }

    @GetMapping("/checkout")
    public String checkout(Model model,@AuthenticationPrincipal MemberDetails memberDetails){

        Member member = memberService.findMember(memberDetails.getMember().getId());
        Cart memberCart = cartService.findMemberCart(member.getId());
        List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
        int totalPrice = cartService.cartTotalPrice(member.getId());

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalCount", memberCart.getCount());
        model.addAttribute("cartListCount", cartItemList.size());
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("member", member);
        return "dailyshop/checkout";
    }

    @GetMapping("/cart")
    public String cart(){
        return "dailyshop/cart";
    }

    @GetMapping("/account")
    public String account(){
        return "dailyshop/account";
    }

    @GetMapping("/error-404")
    public String error404(){
        return "dailyshop/404";
    }
}
