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
     * AdminController로 이동시킴
     */
//    /**
//     * 상품 등록
//     */
//    @GetMapping("/product/register")
//    public String productSaveForm(){
//        return "dailyshop/product-register";
//    }
//    @PostMapping("/product/register")
//    public String productSave(Product product, MultipartFile file) throws IOException {
//        productService.productRegister(product, file);
//        return "redirect:/dailyShop/main";
//    }
//
//    /**
//     * 전체 상품 리스트
//     */
//    @GetMapping("/product/list")
//    public String productList(Model model,
//                              @PageableDefault(sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
//
//        Page<Product> productList = productService.findProducts(pageable);
//        model.addAttribute("list",productList);
//        return "dailyshop/product-list";
//    }

//    /**
//     * 상품 삭제
//     */
//    @GetMapping ("/product/delete")
//    public String productDelete(Long id){
//        productService.productDelete(id);
//        return "redirect:/dailyShop/product/list";
//    }
//    /**
//     * 상품 수정
//     */
//    @GetMapping("/product/modify/{productId}")
//    public String productModifyForm(@PathVariable("productId") Long productId,Model model){
//        Product product = productService.findProduct(productId);
//        model.addAttribute("product",product);
//        return "dailyshop/product-modify";
//    }
//    @PostMapping("/product/modify/{productId}")
//    public String productModify(@PathVariable Long productId,
//                                @ModelAttribute ProductDto form,
//                                MultipartFile file) throws IOException {
//        productService.productModify(productId,form.getName(),file,form.getPrice(),form.getCategory(),form.getStockQuantity());
//        return "redirect:/dailyShop/product/list";
//    }

    /**
     * 상품 상세 페이지
     */
    @GetMapping("/product/view/{productId}")
    public String productView(@PathVariable("productId") Long id, Model model, @AuthenticationPrincipal MemberDetails memberDetails){
        if (memberDetails.getMember().getRole().equals("ROLE_ADMIN")){
            //관리자

            model.addAttribute("product",productService.findProduct(id));
            model.addAttribute("member", memberDetails.getMember());

            return "dailyshop/product-detail";
        }else {
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
            model.addAttribute("member", memberDetails.getMember());

            return "dailyshop/product-detail";
        }
    }

    /**
     * 카테고리별 상품 리스트
     */
    @GetMapping("/product/category/list")
    public String productListByCategory(Model model,Pageable pageable,String category){
        model.addAttribute("products",productService.findByCategory(category,pageable));
        return "dailyshop/product-list-test";
    }
}
