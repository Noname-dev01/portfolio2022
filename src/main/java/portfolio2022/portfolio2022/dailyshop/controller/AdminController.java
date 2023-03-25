package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolio2022.portfolio2022.dailyshop.domain.dto.ProductDto;
import portfolio2022.portfolio2022.dailyshop.domain.entity.*;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class AdminController {

    private final MemberService memberService;
    private final CartService cartService;
    private final ProductService productService;
    private final ChargeListService chargeListService;

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

//    @PostMapping("/admin/product/register")
//    public String productSave(Product product, MultipartFile file) throws IOException {
//        productService.productRegister(product, file);
//        return "redirect:/dailyShop/admin/product/list";
//    }
    @ResponseBody
    @PostMapping(value = "/admin/product/register")
    public String productSave(Product product,@RequestParam("file") MultipartFile file) throws IOException {
        productService.productRegister(product, file);
        return "redirect:/dailyShop/admin/product/list";
    }

    /**
     * 전체 상품 리스트
     */
    @GetMapping("/admin/product/list")
    public String productList(Model model,
                              @PageableDefault(sort = "id",direction = Sort.Direction.DESC) Pageable pageable){

        Page<Product> productList = productService.findProducts(pageable);
        model.addAttribute("list",productList);
        return "dailyshop/admin/product/list";
    }

    /**
     * 상품 삭제
     */
    @GetMapping ("/admin/product/delete")
    public String productDelete(Long id){
        productService.productDelete(id);
        return "redirect:/dailyShop/admin/product/list";
    }

    /**
     * 상품 수정
     */
    @GetMapping("/admin/product/modify/{productId}")
    public String productModifyForm(@PathVariable("productId") Long productId,Model model){
        Product product = productService.findProduct(productId);
        model.addAttribute("product",product);
        return "dailyshop/admin/product/modify";
    }
    @PostMapping("/admin/product/modify/{productId}")
    public String productModify(@PathVariable Long productId,
                                @ModelAttribute ProductDto form,
                                MultipartFile file) throws IOException {
        if (file.isEmpty()){
            return "redirect:/dailyShop/admin/product/modify/{productId}";
        }
        productService.productModify(productId,form.getName(),file,form.getPrice(),form.getCategory(),form.getSubCategory(),form.getStockQuantity(),form.getDescriptionSimple(),form.getDescriptionDetail(),form.getBadge());
        return "redirect:/dailyShop/admin/product/list";
    }

    /**
     * 충전하기
     */
    @GetMapping("/admin/point/{id}")
    public String chargePoint(@PathVariable("id")Long id,Model model){
        model.addAttribute("member",memberService.findMember(id));

        return "dailyshop/admin/member/point";
    }

    @PostMapping("/admin/point/{id}")
    public String chargePoint(@PathVariable("id")Long id,int amount){
        memberService.chargePoint(id, amount);

        return "redirect:/dailyShop/admin/members";
    }

    /**
     * 충전 요청 전체 리스트
     */
    @GetMapping("/admin/charge/list")
    public String adminChargeList(@ModelAttribute("chargeListSearch")ChargeListSearch chargeListSearch, Model model){
        List<ChargeList> chargeListAll = chargeListService.findChargeListSearch(chargeListSearch);

        model.addAttribute("chargeListAll",chargeListAll);
        return "dailyshop/admin/member/charge-list";
    }
    @PostMapping("/admin/charge/{chargeListId}")
    public String adminChargeCheck(@PathVariable("chargeListId")Long chargeListId){
        chargeListService.chargeOkay(chargeListId);
        return "redirect:/dailyShop/admin/charge/list";
    }

    @GetMapping("/admin/charge/delete/{id}")
    public String deleteCharge(@PathVariable("id")Long id){
        chargeListService.deleteChargeList(id);
        return "redirect:/dailyShop/admin/charge/list";
    }
}
