package portfolio2022.portfolio2022.dailyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dailyShop")
public class DailyShopController {

    @GetMapping
    public String home(){
        return "dailyshop/index";
    }

    @GetMapping("/product")
    public String product(){
        return "dailyshop/product";
    }

    @GetMapping("/contact")
    public String contact() {
        return "dailyshop/contact";
    }

    @GetMapping("/product-detail")
    public String productDetail() {
        return "dailyshop/product-detail";
    }

    @GetMapping("/error-404")
    public String error404(){
        return "dailyshop/404";
    }
}
