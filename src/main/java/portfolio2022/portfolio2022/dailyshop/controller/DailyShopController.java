package portfolio2022.portfolio2022.dailyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dailyShop")
public class DailyShopController {

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
    public String home(){
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
