package portfolio2022.portfolio2022.dailyshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DailyShopController {

    @GetMapping("/dailyShop")
    public String home(){
        return "dailyshop/index";
    }


}
