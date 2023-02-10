package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolio2022.portfolio2022.dailyshop.domain.dto.ProductDto;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;
import portfolio2022.portfolio2022.dailyshop.domain.entity.CartItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.CartService;
import portfolio2022.portfolio2022.dailyshop.service.CategoryService;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class ProductController {

    private final ProductService productService;
    private final MemberService memberService;
    private final CategoryService categoryService;
    private final CartService cartService;
    /**
     * 상품 등록,수정,삭제는 AdminController에 있음
     */

    /**
     * 상품 상세 페이지
     */
    @GetMapping("/product/view/{productId}")
    public String productView(@PathVariable("productId") Long id, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        if (memberDetails.getMember() != null){
            //회원
            Member member = memberDetails.getMember();

            Member loginMember = memberService.findMember(member.getId());

            int cartCount = 0;
            Cart memberCart = cartService.findMemberCart(loginMember.getId());
            List<CartItem> cartItems = cartService.allUserCartView(memberCart);

            for (CartItem cartItem : cartItems) {
                cartCount += cartItem.getCount();
            }

            model.addAttribute("cartCount",cartCount);
            model.addAttribute("product",productService.findProduct(id));
            model.addAttribute("member", memberService.findMember(member.getId()));
            return "dailyshop/product-detail";

        }else {

            return "dailyshop/product-detail";
        }
    }

    /**
     * 카테고리별 상품 리스트
     */
    @GetMapping("/product/category/list")
    public String productListByCategory(Model model,Pageable pageable,String category,@AuthenticationPrincipal MemberDetails memberDetails){

        model.addAttribute("products",productService.findByCategory(category,pageable));
        model.addAttribute("member",memberDetails.getMember());
        return "dailyshop/product-list-test";
    }
}
