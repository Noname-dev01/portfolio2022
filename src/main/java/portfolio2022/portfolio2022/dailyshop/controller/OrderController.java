package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.OrderService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping("/order")
    public String orderForm(Model model){
        return null;
    }
}
