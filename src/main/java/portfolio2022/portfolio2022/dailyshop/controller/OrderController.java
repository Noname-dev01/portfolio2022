package portfolio2022.portfolio2022.dailyshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Member;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Order;
import portfolio2022.portfolio2022.dailyshop.domain.entity.OrderItem;
import portfolio2022.portfolio2022.dailyshop.domain.entity.Product;
import portfolio2022.portfolio2022.dailyshop.security.service.MemberDetails;
import portfolio2022.portfolio2022.dailyshop.service.MemberService;
import portfolio2022.portfolio2022.dailyshop.service.OrderSearch;
import portfolio2022.portfolio2022.dailyshop.service.OrderService;
import portfolio2022.portfolio2022.dailyshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dailyShop")
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    /**
     * 주문 내역 조회
     */
    @GetMapping("/mypage/orderHistory/{id}")
    public String orderList(@PathVariable("id")Long id, @AuthenticationPrincipal MemberDetails memberDetails,Model model){
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            List<OrderItem> orderItems = orderService.findMemberOrderItems(id);

            model.addAttribute("orderItems",orderItems);
            model.addAttribute("member",memberService.findMember(id));
        }
        return "dailyshop/order-list";
    }

    /**
     * 상세페이지 주문하기
     */
    @Transactional
    @PostMapping("/product/view/{productId}/member/{id}")
    public String buyProduct(@PathVariable("productId")Long productId, @PathVariable("id")Long id, @AuthenticationPrincipal MemberDetails memberDetails,int count){
        if (Objects.equals(memberDetails.getMember().getId(), id)){
            orderService.order(id, productId, count);
        }
        return "redirect:/dailyShop/main";
    }

}
