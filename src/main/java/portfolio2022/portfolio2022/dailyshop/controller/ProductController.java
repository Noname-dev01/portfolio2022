package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import portfolio2022.portfolio2022.dailyshop.domain.Product;
import portfolio2022.portfolio2022.dailyshop.service.CategoryService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * 상품 등록
     */
    @GetMapping("/product/register")
    public String productSaveForm(){
        return "dailyshop/product-register";
    }
    @PostMapping("/product/register")
    public String productSave(Product product, MultipartFile file) throws IOException {
        productService.productRegister(product, file);
        return "redirect:/dailyShop";
    }

    /**
     * 테스트 페이지
     */
    @GetMapping("/product/test")
    public String productView(Model model, Pageable pageable){
        Page<Product> products = productService.findProducts(pageable);
        model.addAttribute("products",products);
        return "dailyshop/product-test";
    }

    /**
     * 전체 상품 리스트
     */
    @GetMapping("/product/list")
    public String productList(Model model,
                              @PageableDefault(sort = "id",direction = Sort.Direction.DESC) Pageable pageable){

        Page<Product> productList = productService.findProducts(pageable);
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

    /**
     * 카테고리별 상품 리스트
     */
    @GetMapping("/product/category/list")
    public String productListByCategory(Model model,Pageable pageable,String category){
        model.addAttribute("products",productService.findByCategory(category,pageable));
        return "dailyshop/product-list-test";
    }
}
