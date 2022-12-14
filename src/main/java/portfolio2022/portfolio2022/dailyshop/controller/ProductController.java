package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolio2022.portfolio2022.dailyshop.domain.Product;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class ProductController {

    private final ProductService productService;
    /**
     * 상품 등록
     */
    @GetMapping("/product/register")
    public String productSaveForm(){
        return "dailyshop/product-register";
    }
    @PostMapping("/product/register")
    public String productSave(Product product, MultipartFile file) throws IOException {
        productService.productRegister(product,file);

        return "redirect:/dailyShop";
    }

    /**
     * 테스트 페이지
     */
    @GetMapping("/product/test")
    public String productView(Model model){
        List<Product> products = productService.findProducts();
        model.addAttribute("products",products);
        return "dailyshop/product-test";
    }

    /**
     * 전체 상품 리스트
     */
    @GetMapping("/product/list")
    public String productList(Model model){
        List<Product> productList = productService.findProducts();
        model.addAttribute("list",productList);
        return "dailyshop/product-list";
    }
    /**
     * 상품 삭제
     */
    @GetMapping ("/product/delete")
    public String productDelete(Long id){
        productService.productDelete(id);
        return "redirect:/dailyShop/product/list";
    }
    /**
     * 상품 수정
     */
    @GetMapping("/product/modify/{productId}")
    public String productModifyForm(@PathVariable("productId") Long productId,Model model){
        Product product = productService.findProduct(productId);
        model.addAttribute("product",product);
        return "dailyshop/product-modify";
    }
    @PostMapping("/product/modify/{productId}")
    public String productModify(@PathVariable Long productId,
                                @ModelAttribute ProductForm form,
                                MultipartFile file) throws IOException {
        productService.productModify(productId,form.getName(),file,form.getPrice(),form.getCategory(),form.getStockQuantity());
        return "redirect:/dailyShop/product/list";
    }
}
