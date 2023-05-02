package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Cart;
import portfolio2022.portfolio2022.dailyshop.domain.entity.CartItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;
import portfolio2022.portfolio2022.dailyshop.repository.product.ProductListCond;
import portfolio2022.portfolio2022.dailyshop.repository.product.ProductSearchCond;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.CartService;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class ProductController {

    private final ProductService productService;
    private final MemberService memberService;
    private final CartService cartService;
    /**
     * 상품 등록,수정,삭제는 AdminController에 있음
     */

    /**
     * 상품 상세 페이지
     */
    @GetMapping("/product/view/{productId}")
    public String productView(@PathVariable("productId") Long productId, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        if (memberDetails != null){
            //회원
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());
            Product product = productService.findProduct(productId);

            List<Product> relatedProducts = productService.relatedProducts(product.getSubCategory(),productId);

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member", member);
            model.addAttribute("relatedProducts",relatedProducts);
            model.addAttribute("product",productService.findProduct(productId));
            return "dailyshop/product-detail";

        }else {
            Product product = productService.findProduct(productId);
            List<Product> relatedProducts = productService.relatedProducts(product.getSubCategory(),productId);
            model.addAttribute("product",productService.findProduct(productId));
            model.addAttribute("relatedProducts",relatedProducts);
            return "dailyshop/product-detail";
        }
    }

    /**
     * 카테고리별 상품 리스트
     */
    @GetMapping("/product/category/list")
    public String productListByCategory(@ModelAttribute("productListCond") ProductListCond productListCond, String category, Model model, @PageableDefault(sort = "id",direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal MemberDetails memberDetails){
        if (memberDetails != null) {
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member", member);
            model.addAttribute("productsByCategory", productService.findByCategory(category, pageable, productListCond));
            return "dailyshop/product-list-category";
        }else{
            model.addAttribute("productsByCategory", productService.findByCategory(category, pageable, productListCond));
            return "dailyshop/product-list-category";
        }
    }

    /**
     * 서브 카테고리별 상품 리스트
     */
    @GetMapping("/product/subCategory/list")
    public String productListBysubCategory(@ModelAttribute("productListCond") ProductListCond productListCond, String category, String subCategory, Model model, Pageable pageable, @AuthenticationPrincipal MemberDetails memberDetails){
        if (memberDetails != null) {
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member", member);
            model.addAttribute("productsBySubCategory", productService.findBySubCategory(category, subCategory, pageable, productListCond));
            return "dailyshop/product-list-subcategory";
        }else {
            model.addAttribute("productsBySubCategory", productService.findBySubCategory(category, subCategory, pageable, productListCond));
            return "dailyshop/product-list-subcategory";
        }
    }

    /**
     * 홈에서 검색 기능
     */
    @GetMapping("/product/search")
    public String productSearch(@ModelAttribute("productSearchCond") ProductSearchCond productSearchCond, Pageable pageable, String searchKeyword, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        if (memberDetails != null) {
            Member member = memberService.findMember(memberDetails.getMember().getId());
            Cart memberCart = cartService.findMemberCart(member.getId());
            List<CartItem> cartItemList = cartService.allUserCartView(memberCart);
            int totalPrice = cartService.cartTotalPrice(member.getId());

            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("totalCount", memberCart.getCount());
            model.addAttribute("cartListCount", cartItemList.size());
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("member", member);
            model.addAttribute("productSearch", productService.productSearch(searchKeyword, pageable, productSearchCond));
            return "dailyshop/product-search";
        }else {
            model.addAttribute("productSearch", productService.productSearch(searchKeyword, pageable, productSearchCond));
            return "dailyshop/product-search";
        }
    }

}
