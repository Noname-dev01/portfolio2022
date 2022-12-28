package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.Product;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class DailyShopController {

    private final ProductService productService;

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

    @GetMapping
    public String home(Model model){
        model.addAttribute("products", productService.findProducts());
        return "dailyshop/index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "dailyshop/contact";
    }

    @GetMapping("/checkout")
    public String checkout(){
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
